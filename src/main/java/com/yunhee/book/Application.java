package com.yunhee.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// 이 어노테이션이 붙은 곳부터 설정을 읽어가기 때문에 항상 최상단에 위치
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// 내장 WAS 실행
		// 서버에 톰캣을 설치할 필요가 없다.
		SpringApplication.run(Application.class, args);
	}
}
