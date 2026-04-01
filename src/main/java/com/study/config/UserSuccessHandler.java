package com.study.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.study.domain.auth.vo.UserInfoVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		//세션
		String url = "";
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;

		//레벨에 따른 페이지 이동
		int roleLevel = uVo.getRoleLevel();
		if(roleLevel == 1) {
			url = "/main/system/comp/compmng";
		}else if(roleLevel == 2) {
			url = "/main/admin/admmain/main";
		}else if(roleLevel <= 3) {
//			url = "/main/user/main/main";
			url = "/user/post/list";
		}else{
			url = "/user/post/list";
		}	

		

		response.sendRedirect(url);
	}
	

}
