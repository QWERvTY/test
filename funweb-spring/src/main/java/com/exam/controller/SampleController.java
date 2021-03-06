//package com.exam.controller;
//
//import java.util.ArrayList;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.exam.domain.SampleDTO;
//import com.exam.domain.SampleDTOList;
//import com.exam.service.MemberService;
//
//import lombok.Setter;
//
//
///*
//component-scan 태그에 의해 자동으로 빈 등록되는 어노테이션
// :	@Conponent
// 	@Controller, @Service, @Repository
// 	
//
//*/
//
//@Controller
//@RequestMapping("/sample*")
//public class SampleController {
//	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
//	
//	@Setter(onMethod_ = @Autowired)
//	private MemberService memberService;
//	
//	@RequestMapping("")
//	public void basic() {
//		System.out.println("basic...!");
//		logger.info("basic...!");
//	}
//
//	@RequestMapping(value = "/basic", method = RequestMethod.GET)
//	public void basicGet() {
//		System.out.println("basic get...!");
//		logger.info("basic get...!");
//	}
//	 
//	@GetMapping("/basicOnlyGet")
//	public void basicGet2() {
//		System.out.println("basic get only get...!");
//		logger.info("basic get only get...!");
//	}
//	
//	@GetMapping("/ex01")
//	public String ex01(SampleDTO dto) {
//		System.out.println(dto);
//		logger.info("" + dto);
//		System.out.println("name : " + dto.getName());
//		System.out.println("age : " + dto.getAge());
//		return "ex01";
//	}
//	
//	// ex02List?ids=111&ids=222&ids=333&ids=555
//	@GetMapping("/ex02List")
//	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
//		System.out.println("ids : " + ids);
//		logger.info("ids : " + ids);
//		return "ex02";
//	}
//	
//	// list는 SampleDTOList의 필드이름(getter의 메소드명)과 동일
//	// ex02Bean?list[0].name=aaa&list[1].name=bbb
//	// ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbc
//	@GetMapping("/ex02Bean")
//	public String ex02Bean(SampleDTOList list) {
//		System.out.println("list dtos: " + list);
//		logger.info("\nlist dtos: " + list);
//		return "ex02Bean";
//	}
//	
//	// ex04?name=aaa&age=11&page=9
//	@GetMapping("/ex04")
//	public String ex04(@ModelAttribute("dto") SampleDTO dto, @ModelAttribute("page") int page, Model model) {
//		String msg = "dto: " + dto + "\tpage: " + page;
//		System.out.println(msg);
//		logger.info(msg);
//		
//		model.addAttribute("name", "dff");
//		return "redirect:sample/ex04";
////		return "sample/ex04";
//	}
//	
//	
//}
