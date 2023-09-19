package com.myspring.pro29;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
// -> 기본 구조가, 결과 뷰를 반환하는 로직. 
//오늘 배우는 RestController, 결과 반환을 뷰가 아니라, 특정 문자열을 반환(JSON형태)
// 전체 컨트롤러 파일이, 기본 구조가, @Controller 라고해도 
// 부분 메서드를 , 데이터만 전달하는 레스트 방식으로 구조 변경가능.
// 컨트롤러 하위에, 레스트를 섞어서 사용도 가능하다는 점.
public class HomeController {
	
	// log4j , 조금더 기능을 추가한 로그 기록을 담당하는 프로그램.
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/*
	*//**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// 결과 뷰에 , 데이터 전달하기.
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	*/
	// JSONTest 라는 뷰페이지에서, 
	// 클라이언트 -> 서버로, 비동기 통신 방식은 ajax 를 이용해서, 데이터를 
	// post 방식으로, 서버에 전달 테스트 중. 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

//	  return "JSONTest";
		// 기존 뷰를 변경함. board 관련 테스트 뷰.
	  return "JSONTest3";
	}
}
