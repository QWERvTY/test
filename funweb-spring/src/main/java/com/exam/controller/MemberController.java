package com.exam.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.MemberVO;
import com.exam.service.MemberService;

import lombok.Setter;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
//	@InitBinder // String으로 들어오는 객체를 Timestamp로 변환
//	public void InitBinder(WebDataBinder binder) {
//		
//		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
//
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				if (text == null || "".equals(text)) {
//					setValue(null);
//					return;
//				}
//				
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				try {
//					Date parsedDate = sdf.parse(text);
//					setValue(new Timestamp(parsedDate.getTime()));
//				} catch (ParseException e) {
//					e.printStackTrace();
//					setValue(null);
//				}
//			}
//			
//		});
//	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("login 호출");
		return "funweb/member/login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(MemberVO memberVO, HttpSession session) {
		System.out.println(memberVO);
		int check = service.loginCheck(memberVO.getId(), memberVO.getPassword());
		
		if (check != 1) { // 로그인 실패
			String message = null;
			if (check == -1) {
				message = "해당하는 아이디가 없습니다.";
			} else if (check == 0) {
				message = "비밀번호가 다릅니다.";
			} else {
				message = "로그인 에러 발생, 다시 입력해주세요.";
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuffer msg = new StringBuffer();
			msg.append("<script>");
			msg.append("alert('" + message + "');");
			msg.append("history.back();");
			msg.append("</script>");
			
			return new ResponseEntity<>(msg.toString(), headers, HttpStatus.OK);
		}
		session.setAttribute("sessionID", memberVO.getId());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/"); // redirect 경로 위치
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		System.out.println("logout!");
		session.invalidate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuffer msg = new StringBuffer();
		msg.append("<script>");
		msg.append("alert('로그아웃 되었습니다.');\n");
		msg.append("location.href='/';");
		msg.append("</script>");
		return new ResponseEntity<>(msg.toString(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/join")
	public String join() {
		System.out.println("join 호출");
		return "funweb/member/join";
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberVO memberVO) {
		System.out.println(memberVO);
		service.register(memberVO);
		
//		return "redirect:/member/login";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		StringBuffer msg = new StringBuffer();
		msg.append("<script>");
		msg.append("alert('회원 가입이 완료되었습니다.');");
		msg.append("location.href='/member/login';");
		msg.append("</script>");
		ResponseEntity<String> responseEntity = new ResponseEntity<>(msg.toString(), headers, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/joinIdCheck")
	public String joinIdCheck(@RequestParam("userID") String userID, Model model) {
		System.out.println("userID : " + userID);
		
//		int count = service.countById(userID);
//		
//		boolean isDup = false;
//		if (count > 0) {
//			isDup = true;
//		}
		boolean isDup = service.isIdDupChecked(userID);
		
		// Id 중복 확인 결과값을 request 객체에 저장
		model.addAttribute("isDup", isDup);
		return "funweb/member/joinIdCheck";
	}
	
	@GetMapping("/joinIdCheckJson")
	public @ResponseBody boolean joinIdCheckJson(String userID) {
//		int count = service.countById(userID);
//		
//		boolean isDup = false;
//		if (count > 0) {
//			isDup = true;
//		}

		return service.isIdDupChecked(userID);
	}
	
}
