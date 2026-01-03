package com.study.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserLoginFailHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errorMessage="";
		if(exception instanceof BadCredentialsException) {
			errorMessage="비번 틀림";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			errorMessage="ID 확인";
		
		}else if(exception instanceof DisabledException) {
			errorMessage="유효하지 않은 사용자입니다";
		}else {
			errorMessage="로그인 실패";
		}
		
		errorMessage=URLEncoder.encode(errorMessage, "UTF-8");
		response.sendRedirect("/login?errorMessage="+errorMessage);
		
	}
	

}
