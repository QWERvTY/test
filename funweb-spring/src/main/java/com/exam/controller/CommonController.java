package com.exam.controller;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.domain.BoardVO;
import com.exam.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	@Setter(onMethod_ = @Autowired)
	private Timer timer;
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	@PostConstruct // 이 객체의 bin을 생성한 후 자동으로 호출할 메소드
	public void init(/* HttpServletRequest request */) {
		log.info("LOG, init() 호출됨");
		System.out.println("init() 호출됨");
//		ServletContext application = request.getServletContext();
//		Timer timer = new Timer(true); // true : Timer를 데몬 스레드 방식으로
//		application.setAttribute("timer", timer);
	}
	
	@GetMapping("/") 
	public String main(Model model) {
		System.out.println("main 호출");
		
		List<BoardVO> list = service.getBoards(1, 5, null);
		System.out.println("list : " + list);
		model.addAttribute("list", list);
		
		return "funweb/main";
		// src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 위치의 beans:bean 옵션으로 지정
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		System.out.println("welcome 호출");
		return "funweb/company/welcome";
	}

	@GetMapping("/batch/form")
	public void batchForm() {
		log.info("batchForm 호출");
		System.out.println("batchForm 호출");
	}
	
	@PostMapping("/batch/process")
	public void batchProcess(@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm") Date datetime, @RequestParam(required = false) Long period, HttpServletRequest request) {
		System.out.println("datetime : " + datetime);
		System.out.println("period : " + period);
		ServletContext application = request.getServletContext();
		
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				System.out.println("<< task1 >>");
			}
		};
		
		timer.scheduleAtFixedRate(task1, 0, period);
		application.setAttribute("task1", task1);
		
	}
	
	@GetMapping("/batch/cancel")
	public void batchCancel(HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		
		TimerTask task1 = (TimerTask) application.getAttribute("task1");
		if (task1 != null ) {
			task1.cancel();
		}
	}
	
}
