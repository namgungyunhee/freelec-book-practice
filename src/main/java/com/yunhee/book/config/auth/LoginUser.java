package com.yunhee.book.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
// 어노테이션 생성 위치를 지정, parameter로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용 가능
@Target(ElementType.PARAMETER)
// 이 파일을 어노테이션 클래스로 지정
// LoginUser라는 어노테이션 생성
public @interface LoginUser {
}
