package com.yunhee.book.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.yunhee.book.domain.user.Role;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
// Spring security 설정들을 활성화 시켜줌
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션을 disable 함
                .and()
                    // URL 별 권한 관리를 설정하는 옵션의 시작점
                    // 이게 선언되어야 antMatchers를 사용할 수 있음
                    .authorizeRequests()
                    // 권한관리 대상을 지정하는 옵션, URL, HTTP별 관리 가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 나머지 URL들은 모두 인증된 사용자들에게만 허용하도록 설정, 즉 로그인한 사용자만
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능에 대한 여러가지 설정의 진입점, 로그인 성공 후 "/" 패스로 이동시킨다.
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    // oauth2 로그인 기능에 대한 여러 설정 진입점
                    .oauth2Login()
                        // Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속조치를 진행할 User service 인터페이스의 구현체를 등록
                            // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                            // eg. 실질적인 회원가입이라던지, 회원중복 여부 등..
                            .userService(customOAuth2UserService);

    }
}
