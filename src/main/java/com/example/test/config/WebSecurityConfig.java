package com.example.test.config;

import com.example.test.handler.UserLoginFailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.test.service.UserService;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {                         // Login Controll Config

  private final UserService userService;                                                      // 요청을 처리하기 위한 UserService

  @Autowired
  public UserLoginFailHandler userLoginFailHandler;                                           // 로그인 실패 Handler

  @Bean 
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }                                                                                           // 비밀번호 암호화 Encoder 선언

  @Override
  public void configure(WebSecurity web) {                                                    // Security Config
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/bootstrap/**");   // 인증정보가 없어도 접근할 수 있는 PATH 선언
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {                              // HTTP Request Config
    http
          .authorizeRequests()                                                                // 요청에 대한 접근 권한 설정
            .antMatchers("/login", "/signup").permitAll()                          // 누구나 접근 허용
            .antMatchers("/").hasRole("USER")                                      // USER권한이 있어야 접근가능
            .anyRequest().authenticated()                                                     // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
        .and() 
          .formLogin()                                                                        // 로그인 과정에 대한 설정
            .loginPage("/login")                                                              // 로그인 페이지 링크
            .defaultSuccessUrl("/")                                                           // 로그인 성공 후 리다이렉트 주소
            .failureHandler(userLoginFailHandler)                                             // 로그인 실패 핸들러
        .and()
          .logout()                                                                           // 로그아웃 과정에 대한 설정
            .logoutSuccessUrl("/login")                                                       // 로그아웃 성공시 리다이렉트 주소
	    .invalidateHttpSession(true)                                                          // 세션 날리기
    ;
    http.csrf().disable();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {                 // 로그인 할 때 필요한 정보에 대한 config
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());                  // 인증에 사용할 Service와 Password 인코더를 설정
  }
}