package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.service.AttachService;
import com.exam.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
	private AttachService attachService;
	
	@GetMapping("/write")
	public String write() {
		System.out.println("write, GET");
		log.info("write()");
		return "funweb/center/write";
	}
	
	@PostMapping("/write")
	public String write(BoardVO board, HttpServletRequest request, HttpSession session) {
		System.out.println("write, POST");
		String sessionID = (String) session.getAttribute("sessionID");
		if (sessionID == null) {
			sessionID = "Unkwnown";
			return "redirect:/ByeBye";
		}
//		sessionID = (sessionID == null) ? "Unknown" : sessionID;
		int num = service.getSeqBoardNum();
		board.setName(sessionID);
		board.setIp(request.getRemoteAddr());
		board.setNum(num);
		board.setReRef(num);
		board.setReLev(0);
		board.setReSeq(0);
		
		service.insert(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int pageNum, 
			@RequestParam(required = false) String search, Model model) {
		System.out.println("list, GET");
		int amount = 10, pageBlockSize = 5; // pageSize = 10,(amount)
		List<BoardVO> list = service.getBoards(pageNum, amount, search);
		System.out.println("list : " + list);
		
		int allRowCount = 0;
		allRowCount = service.getBoardCount(search);
		
		int maxPage = allRowCount / amount + ((allRowCount % amount > 0) ? 1 : 0);
	
		int startPage = (pageNum / pageBlockSize - ((pageNum % pageBlockSize == 0) ? 1 : 0)) * pageBlockSize + 1;
		
		int endPage = startPage + pageBlockSize - 1;
		endPage = (endPage > maxPage) ? maxPage : endPage;
		
		Map<String, Integer> pageInfoMap = new HashMap<>();
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		pageInfoMap.put("pageBlockSize", pageBlockSize);
		pageInfoMap.put("maxPage", maxPage);
		pageInfoMap.put("allRowCount", allRowCount);
		pageInfoMap.put("pageNum", pageNum);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("search", search);
		model.addAttribute("list", list);
		
		return "funweb/center/notice";
	}
	
	@GetMapping("/detail")
	public String detail(int num, Model model, HttpSession session) {
		System.out.println("detail, GET");
		BoardVO board = service.getBoard(num);
		String content = "";
		if (board.getContent() != null) {
			content = board.getContent().replace("\r\n", "<br>");
			board.setContent(content);
		}
		String sessionID = (String) session.getAttribute("sessionID");
		if (!board.getName().equals(sessionID)) {
			service.updateReadCount(num);
			board.setReadCount(board.getReadCount() + 1);
		}
		System.out.println("board : " + board);
		model.addAttribute("board", board);
		return "funweb/center/boardView";
	}
	
	@GetMapping("/modify")
	public String modify(int num, Model model) {
		System.out.println("modify, GET");
		BoardVO board = service.getBoard(num);
		model.addAttribute("board", board);
		return "funweb/center/update";
	}
	
	@PostMapping("/modify")
	public ResponseEntity<String> modify(BoardVO board, String pageNum) {
		log.info("modify, POST : " + board);
		System.out.println("modify, POST : " + board);
		System.out.println("pageNum : " + pageNum);
		boolean isSuccess = service.updateBoard(board);
		
		if (!isSuccess) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
            sb.append("alert('글 비밀번호가 다릅니다.');");
            sb.append("history.back();");
            sb.append("</script>");
			
            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/board/list?pageNum=" + pageNum);
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	@GetMapping("/delete")
	public String delete() {
		System.out.println("delete, GET");
		return "funweb/center/delete";
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete(int num, String pass, String pageNum) {
		boolean isSuccess = service.deleteBoard(num, pass);
		
		if (!isSuccess) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
            sb.append("alert('글 비밀번호가 다릅니다.');");
            sb.append("history.back();");
            sb.append("</script>");
            
            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/board/list?pageNum=" + pageNum);
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	@GetMapping("/reply")
	public String reply() {
		System.out.println("reply, GET");
		return "funweb/center/replyWrite";
	}
	
	@PostMapping("/reply")
	public String reply(BoardVO board, String pageNum, HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		System.out.println("reply, POST");
		board.setIp(request.getRemoteAddr());
		String sessionID = (session.getAttribute("sessionID") == null) ? "Unknown" : (String) session.getAttribute("sessionID");
		board.setName(sessionID);
		
		service.replyInsert(board);
		rttr.addAttribute("pageNum", pageNum);
		return "redirect:/board/list";
	}
	
	@GetMapping("/filelist")
	public String filelist(@RequestParam(defaultValue = "1") int pageNum, 
			@RequestParam(required = false) String search, Model model) {
		System.out.println("filelist, GET");
		int amount = 10, pageBlockSize = 5; // pageSize = 10,(amount)
		List<BoardVO> list = service.getBoards(pageNum, amount, search);
		System.out.println("filelist : " + list);
		
		int allRowCount = 0;
		allRowCount = service.getBoardCount(search);
		
		int maxPage = allRowCount / amount + ((allRowCount % amount > 0) ? 1 : 0);
	
		int startPage = (pageNum / pageBlockSize - ((pageNum % pageBlockSize == 0) ? 1 : 0)) * pageBlockSize + 1;
		
		int endPage = startPage + pageBlockSize - 1;
		endPage = (endPage > maxPage) ? maxPage : endPage;
		
		Map<String, Integer> pageInfoMap = new HashMap<>();
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		pageInfoMap.put("pageBlockSize", pageBlockSize);
		pageInfoMap.put("maxPage", maxPage);
		pageInfoMap.put("allRowCount", allRowCount);
		pageInfoMap.put("pageNum", pageNum);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("search", search);
		model.addAttribute("list", list);
		
		return "funweb/center/fileNotice";
	}
	
	@GetMapping("/fileWrite")
	public String fileWrite() {
		System.out.println("fileWrite, GET");
		return "funweb/center/fileWrite";
	}
	
	@PostMapping("/fileWrite")
	public String fileWrite(BoardVO board, HttpServletRequest request, HttpSession session, MultipartFile[] files) { // (... , UploadDTO upload) - MultipartFile 사용하지 않고 파일 복수개
		System.out.println("fileWrite, POST");
		String sessionID = (String) session.getAttribute("sessionID");
		if (sessionID == null) {
			sessionID = "Unkwnown";
			return "redirect:/ByeBye";
		}
		int num = service.getSeqBoardNum();
		board.setIp(request.getRemoteAddr());
		board.setName(sessionID);
		board.setNum(num);
		board.setReRef(num);
		board.setReLev(0);
		board.setReSeq(0);
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
//		String uploadRealPath = "C:/upload/";
		
		log.info("realPath : " + realPath);
		System.out.println("realPath : " + realPath);
		
		// 폴더 동적 생성하기
		File uploadPath = new File(realPath, getFolder());
		System.out.println("uploadPath : " + uploadPath);
		
		if (!uploadPath.exists()) {
			uploadPath.mkdirs(); // 업로드할 폴더들 생성
		}
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		for (MultipartFile m : files) {
			System.out.println("업로드 파일명 : " + m.getOriginalFilename());
			System.out.println("업로드 파일 크기 : " + m.getSize());
			System.out.println(m.isEmpty());
			if (m.isEmpty()) {
				continue;
			}
			String uploadFileName = m.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			System.out.println("최종 파일명 : " + uploadFileName);
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				m.transferTo(saveFile); // 업로드 수행
				System.out.println("업로드 수행!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			AttachVO attachVO = new AttachVO();
			attachVO.setBNum(board.getNum());
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadPath(getFolder());
			attachVO.setFileName(m.getOriginalFilename());
		
			if (checkImageType(saveFile)) {
				attachVO.setFileType("I"); // Image fileType
				// 섬네일 이미지 생성
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				try {
					FileOutputStream fos = new FileOutputStream(thumbnailFile);
					
					Thumbnailator.createThumbnail(m.getInputStream(), fos, 100, 100);
					fos.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				attachVO.setFileType("O"); // Other fileType
			}
		
			attachList.add(attachVO);
		}
		service.insertBoardANDAttach(board, attachList);
		
		return "redirect:/board/filelist";
	}
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String str = sdf.format(date);
		
//		System.out.println("File.separator : " + File.separator);
//		String folderName = str.replace("-", File.separator);
//		System.out.println("folderName : " + folderName);
//		return folderName;
		return str;
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info("contentType : " + contentType);
			System.out.println("contentType : " + contentType);
			
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@GetMapping("/fileDetail")
	public String fileDetail(int num, HttpSession session, Model model) {
		BoardVO board = service.getBoard(num); // 글 번호에 해당하는 글 전체 내용
		List<AttachVO> attachList = service.findByBNum(num); // 글 번호에 해당하는 첨부파일 리스트
		String content = "";
		if (board.getContent() != null) {
			content = board.getContent().replace("\r\n", "<br>");
			board.setContent(content);
		}
		String sessionID = (String) session.getAttribute("sessionID");
		if (!board.getName().equals(sessionID)) {
			service.updateReadCount(num);
			board.setReadCount(board.getReadCount() + 1);
		}
		System.out.println("board : " + board);
		model.addAttribute("attachList", attachList);
		model.addAttribute("board", board);
		
		return "funweb/center/boardFileView";
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		log.info("LOG, download file : " + fileName);
		System.out.println("download file : " + fileName);
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		
		Resource resource = new FileSystemResource(realPath + "/" + fileName);
		
		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		log.info("LOG, resource : " + resource);
		System.out.println("resource : " + resource);
		
		String resourceName = resource.getFilename();
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		// 전체 이름에서 uuid값 제거한 원래 파일 이름
		
		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = null;
			
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				System.out.println("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			} else if (userAgent.contains("Edge")) {
				log.info("Edge browser");
				System.out.println("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			} else {
				log.info("Chrome browser");
				System.out.println("Chrome browser");
				
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			log.info("downloadName : " + downloadName);
			System.out.println("downloadName : " + downloadName);
			
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@GetMapping("/fileDelete")
	public String fileDelete() {
		return "funweb/center/fileDelete"; 
	}
	
	@PostMapping("/fileDelete")
	public ResponseEntity<String> fileDelete(int num, String pass, String pageNum, HttpSession session, HttpServletRequest request) {
		BoardVO board = service.getBoard(num);
		String sessionID = (String) session.getAttribute("sessionID");
		if (!board.getName().equals(sessionID) && !"admin".equals(sessionID)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}
		
		if (!board.getPass().equals(pass)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
            sb.append("alert('글 비밀번호가 다릅니다.');");
            sb.append("history.back();");
            sb.append("</script>");
            
            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		List<AttachVO> attachList = service.findByBNum(num);
		System.out.println("attachList : " + attachList);
		System.out.println("attachListSize : " + attachList.size());
		if (attachList.size() > 0) {
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload");
			
			for (AttachVO attach : attachList) {
				String path = realPath + "/" + attach.getUploadPath();
				String fileName = attach.getUuid() + "_" + attach.getFileName();
				System.out.println(attach.getFileType());
				if (attach.getFileType().equals("I")) { // 섬네일 삭제하기
					File thumbnailFile = new File(path, "s_" + fileName);
					System.out.println("섬네일 파일 " + thumbnailFile);
					if (thumbnailFile.exists()) {
						System.out.println("섬네일 삭제");
						thumbnailFile.delete();
					}
				}
				// 원본 파일 삭제하기
				File deleteFile = new File(path, fileName);
				System.out.println("실제 파일 " + deleteFile);
				if (deleteFile.exists()) {
					System.out.println("실제 파일 삭제");
					deleteFile.delete();
				}
			}
		}
		service.deleteBoardANDAttach(num);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/board/filelist?pageNum=" + pageNum);
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	
	
	@GetMapping("/fileModify")
    public String fileModify(int num, Model model) {
        BoardVO board = service.getBoard(num);
        List<AttachVO> attachList = service.findByBNum(num);
        
        model.addAttribute("board", board);
        model.addAttribute("attachList", attachList);
        
        return "funweb/center/fileUpdate";
    }
    
    
	@PostMapping("/fileModify")
    public ResponseEntity<String> fileModify(BoardVO board, String[] delFiles, MultipartFile[] newFiles, String pageNum, HttpServletRequest request) {
        // 글 패스워드 일치하면 글수정 후 글목록으로 이동
        // 글 패스워드 불일치하면 이전화면으로 돌아가기
        // boolean isSuccess == true 수정성공
        //         isSuccess == false 수정실패
        boolean isSuccess = service.updateBoard(board);
        
        if (!isSuccess) { // // 글 수정 실패
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            StringBuilder sb = new StringBuilder();
            sb.append("<script>");
            sb.append("alert('글비밀번호가 틀립니다.');");
            sb.append("history.back();");
            sb.append("</script>");

            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
        }
        
        
        ServletContext application = request.getServletContext();
        String realPath = application.getRealPath("/upload");
        
        
        // 2019/05/21/64b74a73-d669-42af-8d6d-fb295742863d_이미지파일.png
        if (delFiles != null) {
            for (int i=0; i<delFiles.length; i++) {
                String folder = delFiles[i].substring(0, 10); // 2019/05/21
                String uuidAndFilename = delFiles[i].substring(11); // 2019/05/21/ 제외
                
                int index = uuidAndFilename.indexOf("_");
                String uuid = uuidAndFilename.substring(0, index);
                String fileName = uuidAndFilename.substring(index + 1);
                log.info("uuid : " + uuid);
                log.info("filename : " + fileName);
                System.out.println("uuid : " + uuid);
                System.out.println("filename : " + fileName);
                
                
                // 첨부파일 삭제항목 삭제하기
                File deleteFile = new File(realPath + "/" + delFiles[i]);
                if (deleteFile.exists()) deleteFile.delete();
                
                // 섬네일 삭제하기
                File thumbnailFile = new File(realPath + "/" + folder, "s_" + uuid + "_" + fileName);
                if (thumbnailFile.exists()) thumbnailFile.delete();
                
                
                // attach 테이블에서  행 삭제
                attachService.delete(uuid);
            } // for
        } // if
        
        
        // 폴더 동적 생성하기   /upload/2019/05/17
        File uploadPath = new File(realPath, getFolder());
        log.info("uploadPath : " + uploadPath);
        System.out.println("uploadPath : " + uploadPath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // 업로드할 폴더 생성
        }

        // 새로 추가된 첨부파일 정보를 담을 리스트 준비
        List<AttachVO> newAttachList = new ArrayList<>();
        // 추가된 첨부파일 업로드하기
        for (MultipartFile multipartFile : newFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            
            String uploadFileName = multipartFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            log.info("업로드할 최종 파일명: " + uploadFileName);
            
            File saveFile = new File(uploadPath, uploadFileName);
            try {
                multipartFile.transferTo(saveFile); // 업로드 수행
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            AttachVO attachVO = new AttachVO();
            attachVO.setBNum(board.getNum()); // 게시글번호 저장
            attachVO.setUuid(uuid.toString());  // 첨부파일 UUID
            attachVO.setUploadPath(getFolder());
            attachVO.setFileName(multipartFile.getOriginalFilename());
            
            
            if (checkImageType(saveFile)) { // 이미지 파일이면
                // 섬네일 이미지 생성하기
                File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
                try {
                    FileOutputStream fos = new FileOutputStream(thumbnailFile);
                    
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
                    
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                attachVO.setFileType("I"); // Image file type
            } else {
                attachVO.setFileType("O"); // Other file type
            }
            
            newAttachList.add(attachVO);
        } // for
        
        for (AttachVO attachVO : newAttachList) {
            log.info("attachVO : " + attachVO);
            System.out.println("attachVO : " + attachVO);
        }
        
        // 추가된 첨부파일 테이블 insert하기
        if (newAttachList.size() > 0) {
            attachService.insert(newAttachList);
        }
        
        // 글 수정 성공 이후 글 목록으로 리다이렉트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/board/filelist?pageNum=" + pageNum); // redirect 경로 위치
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    } // fileModify() post
	
	
	
	
	
	
	
	
	
	
}
