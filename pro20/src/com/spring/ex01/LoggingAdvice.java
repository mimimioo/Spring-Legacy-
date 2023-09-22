package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 보조기능 클래스 , MethodInterceptor 인터페이스 구현 후, 추상 메서드 의무적 재정의
public class LoggingAdvice implements MethodInterceptor {
	// 주기능이 1) 실행 되기 전, 2) 실행 되기 후, 3) 예외발생시 
	// 이 invoke 메서드가 실행이됨. 그렇게 설계됨. 
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("[메서드 호출 전 : LogginAdvice");
		System.out.println(invocation.getMethod() + "메서드 호출 전");

		Object object = invocation.proceed();

		System.out.println("[메서드 호출 후 : loggingAdvice");
		System.out.println(invocation.getMethod() + "메서드 호출 후");
		return object;
	}
}
