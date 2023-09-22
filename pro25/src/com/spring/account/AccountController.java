package com.spring.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountController  extends MultiActionController  {
	// 동네1
	// 동네2 외주 주기위한, 인스턴스 선언 및 초기화 부분
	   private AccountService accService ; 
	   public void setAccService(AccountService accService){
	     this.accService = accService;
	   }	

	   // 실제 동작 확인 부분, 
	   public ModelAndView sendMoney(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ModelAndView mav=new ModelAndView();
	      // 실제 작업 , 동네 2 외주 주기. 
	      accService.sendMoney();
	      mav.setViewName("result");
	      return mav;
	   }
}
