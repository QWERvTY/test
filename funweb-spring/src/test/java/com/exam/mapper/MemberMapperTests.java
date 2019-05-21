package com.exam.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testInsert() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("admin");
		memberVO.setPassword("admin");
		memberVO.setName("TY");
		memberVO.setGender("M");
		
		int count = mapper.insert(memberVO);
        
        log.info(memberVO);
        log.info("insert한 행 개수: " + count);
	}
	
	@Test
	public void testGetList() {
		List<MemberVO> list = mapper.getAllMember();
		for (MemberVO member : list) {
			log.info(member);
		}
		
	}
	
	@Test
	public void testGetMemberById() {
		MemberVO memberVO = mapper.getMemberById("user1");
		log.info(memberVO);
	}
	
	@Test
	public void testDelete() {
		int count = mapper.delete("us23233");
//		MemberVO memberVO = mapper.delete("user1");
		log.info("DELETE 한 행 개수 : " + count);
	}
	
	@Test
	public void testUpdate() {
		MemberVO memberVO = mapper.getMemberById("us233");
		memberVO.setPassword("1234");
		memberVO.setName("King");
		
		int count = mapper.update(memberVO);
		
		log.info("UPDATE 한 행 개수 : " + count);
	}
	
	@Test
	public void testCountById() {
		int count = mapper.countById("user1");
		
		log.info("UPDATE 한 행 개수 : " + count);
	}
}
