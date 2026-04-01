package com.study.domain.auth.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.aop.around.MethodLog;
import com.study.common.CommonController;
import com.study.domain.auth.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends CommonController{
	
	@MethodLog
    @GetMapping("/login")
    public String login(Model model,  @RequestParam(required = false, value = "errorMessage") String errorMessage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            model.addAttribute("errorMessage", errorMessage);
            return "member/login";
        }

        //역할에 따른 페이지 이동
        String result = "";
        UserInfoVO uVo = getUserInfo();
        String roleSecurity = uVo.getRoleSecurity();
        if(roleSecurity.equals("ROLE_SYSTEM")) {
        	result = "redirect:/main/system/comp/compmng";
//        }else if(roleSecurity.equals("ROLE_ADMIN") || roleSecurity.equals("ROLE_MASTER")) {
//        	result = "redirect:/admin/main";
        }else if(roleSecurity.equals("ROLE_USER")) {
        	result = "redirect:/user/post/list";
        }else{
        	result = "redirect:/login";
        }

        return result;
    }
	

}
