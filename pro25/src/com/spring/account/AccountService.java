package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*@Transactional(propagation=Propagation.REQUIRED)*/
public class AccountService {
	// 동네2 , 해당 클래스를 , 애너테이션 기법을 이용해서
	// 트랜잭션을 설정 했음. 
	
	// 동네3 , 선언 및 초기화 부분 
	private AccountDAO accDAO;

	public void setAccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	}

	// 비지니스 로직에서, 하나의 기능에, 2가지의 sql 문장을 그룹 묶음. 
	public void sendMoney() throws Exception {
		// 실제작업 동네 3번으로 외주 .
		accDAO.updateBalance1();
		accDAO.updateBalance2();
	}
}


