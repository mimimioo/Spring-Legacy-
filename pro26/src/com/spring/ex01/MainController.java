package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
//클래스 범위
@RequestMapping("/test")
public class MainController {
//	메서드 범위
   @RequestMapping(value="/main1.do" ,method=RequestMethod.GET)
   // main1 메서드 
   public ModelAndView main1(HttpServletRequest request, HttpServletResponse response)  throws Exception{
	   
      ModelAndView mav=new ModelAndView();
      // 결과 뷰에 전달하는 데이터 설정
      mav.addObject("msg","main1");
      // 클라이언트에게 전달하는 결과 뷰.
      mav.setViewName("main");
      return mav;
   }

   @RequestMapping(value="/main2.do" ,method = RequestMethod.GET)
   public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception{
      ModelAndView mav=new ModelAndView();
      mav.addObject("msg","main2");
      mav.setViewName("main");
      return mav;
   }
}
