package com.example.test.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class UserLoginFailHandler implements AuthenticationFailureHandler {                 // Login 실패시 예외처리를 하기 위한 Handler

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,                         // Login 실패시 호출되는 함수
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {


        if (exception instanceof AuthenticationServiceException) {                          // 로그인 실패 이유를 확인하고 해당 경우에 맞는 메시지를 생성.
            request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");

        } else if(exception instanceof BadCredentialsException) {
            request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");

        } else if(exception instanceof LockedException) {
            request.setAttribute("loginFailMsg", "잠긴 계정입니다..");

        } else if(exception instanceof DisabledException) {
            request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");

        } else if(exception instanceof AccountExpiredException) {
            request.setAttribute("loginFailMsg", "만료된 계정입니다..");

        } else if(exception instanceof CredentialsExpiredException) {
            request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/login");          // 로그인 페이지로 다시 포워딩
        dispatcher.forward(request, response);
    }
}
