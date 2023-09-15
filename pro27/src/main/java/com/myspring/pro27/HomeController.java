package com.myspring.pro27;

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
public class HomeController {
  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  /**
  * Simply selects the home view to render by returning its name.
  */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);

    // 나중에, 파일명에서, 중복파일 피하기위서 사용하는 네이밍 기법 중하나인데.
    // 1) 시간 및 날짜를 이용하고,
    // 2) UUID , 특정 랜덤한 숫자 및 영문자를 생성해주는 도구를 이용하기도함.
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, 
    DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);
    // 단순 데이터만, 뷰에 전달하는 구조만 잠시 보면 됨. 
    model.addAttribute("serverTime", formattedDate );
    // 결과 뷰는, 모델&뷰 형식이 아니라, 단순 뷰 리졸버 해당 뷰로 이동함. 
//    return "home";
    // 뷰 리졸벌 대신, 타일즈를 이용해서, 메인에 접속해보기.
    return "main";
  }
}


//�ٱ��� ��� �ڵ�
/*
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
}

*/