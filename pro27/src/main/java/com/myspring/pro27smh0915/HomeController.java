package com.myspring.pro27smh0915;

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

    // ?��중에, ?��?��명에?��, 중복?��?�� ?��?��기위?�� ?��?��?��?�� ?��?���? 기법 중하?��?��?��.
    // 1) ?���? �? ?��짜�?? ?��?��?���?,
    // 2) UUID , ?��?�� ?��?��?�� ?��?�� �? ?��문자�? ?��?��?��주는 ?��구�?? ?��?��?��기도?��.
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, 
    DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);
    // ?��?�� ?��?��?���?, 뷰에 ?��?��?��?�� 구조�? ?��?�� 보면 ?��. 
    model.addAttribute("serverTime", formattedDate );
    // 결과 뷰는, 모델&�? ?��?��?�� ?��?��?��, ?��?�� �? 리졸�? ?��?�� 뷰로 ?��?��?��. 
//    return "home";
    // �? 리졸�? ???��, ???��즈�?? ?��?��?��?��, 메인?�� ?��?��?��보기.
    return "main";
  }
}


//�ٱ��� ���? �ڵ�
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