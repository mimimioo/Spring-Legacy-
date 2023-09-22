package com.spring.ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

// 자바의 특성상, 메인 메서드에서 실행을 합니다. 
public class PersonTest {

	// 메인 메서드에서, 
	public static void main(String[] args) {
		//  new FileSystemResource("person.xml") 
		//-> 초기화를 할 설정 파일 -> 해당 클래스의 인스턴스 
		// 생성하기 위한 목적
		// new XmlBeanFactory -> 해당 인스턴스를 생성하는 파일을 읽은 인스턴스
		// factory 라는 참조형 변수에 초기화를 했다라고 표현. 
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		//factory 라는 인스턴스에 내부에는 여러 가지 멤버(변수), 메서드(기능) 포함이 되어있음.
		// 참고로 어느 클래스이건, 해당 인스턴스에 모두 속성의 구성품을 다 알고 쓰는 건 아니다.
		// 클래스? -> 많은 멤버, 함수 기능들의 모음집 
		// 패키지 -> 클래스들의 모음집. 
		// 모듈 -> 패키지들의 모음집. 
		// 라이브러링 -> 해당 기능들의 모음 집.
		PersonService person = (PersonService) factory.getBean("personService");
		// PersonService person = new PersonServiceImpl();
		// 메인 메소드가 있는 클래스에서 -> 우클릭에서, 자바 로 단순 실행. 
		//
		person.sayHello();
		
		
		//평상시, PersonServiceImpl
		System.out.println("보통 일반적인 개발자들이 객체 인스턴스를 생성하는 경우.");
		PersonServiceImpl p1 = new PersonServiceImpl();
		p1.setName("이상용");
		p1.sayHello();
		
		
	}

}
