package com.spring.ex01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {
	   public static void main(String[] args){
		   // xml 파일에 있는 빈(인스턴스) proxyCal 를 불러옴
	      ApplicationContext context=new ClassPathXmlApplicationContext("AOPTest.xml");
	      Calculator cal=(Calculator)context.getBean("proxyCal");
	      
	      // 주기능, 보조기능을 묶어 놓은 인스턴스 proxyCal 를 불러와, cal 재 할당 
	      // 그리고 실행시, 주기능 동작 전, 후에 , 보조기능인 로그 어드바이스가 실행이 되는 구조.
	      cal.add(100,20);
	      System.out.println();
	      cal.subtract(100,20);
	      System.out.println();
	      cal.multiply(100,20);
	      System.out.println();
	      cal.divide(100,20);
	   }
	}

