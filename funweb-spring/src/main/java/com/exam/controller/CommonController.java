package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.domain.BoardVO;
import com.exam.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;

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
	public String batchForm() {
		log.info("batchForm 호출");
		System.out.println("batchForm 호출");
		return "batch/form";
	}
	
}
