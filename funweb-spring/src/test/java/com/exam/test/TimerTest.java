package com.exam.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

public class TimerTest {
	
	public static void main(String[] args) {
		Timer timer = new Timer(); // isDaemon, 데몬 스레드 방식
		
		TimerTask timerTask1 = new TimerTask() {
			@Override
			public void run() {
				System.out.println("<< timerTask1 >>");
//				log.info("LOG, timerTask1...");
			}
		};
		
//		String str = "2019-05-21T14:26";
//		str = str.replace("T", " ");
//		System.out.println(str);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		Date date = null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
	
		TimerTask timerTask2 = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("## timerTask2 ##");
			}
		};
		
		timer.scheduleAtFixedRate(timerTask1, 500, 2000);
		timer.scheduleAtFixedRate(timerTask2, 0, 2000);
	}
	
//	@Test
//	public void testTimer() {
//		Timer timer = new Timer(true); // isDaemon, 데몬 스레드 방식
//		TimerTask timerTask1 = new TimerTask() {
//			
//			@Override
//			public void run() {
//				System.out.println("<< timerTask1 >>");
////				log.info("LOG, timerTask1...");
//			}
//		};
//		
//		String str = "2019-05-21T14:26";
//		str.replace("T", " ");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Date date = null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		timer.scheduleAtFixedRate(timerTask1, date, 2000);
//	}
}
