package com.study.domain.member;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.common.dto.SearchDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller //댓글과는 달리 회원가입 페이지가 필요하기 때문에 @RestController가 아닌 @Controller와 @ResponseBody가 사용
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login.do")
    public String openLogin() {
        return "member/login";
    }
    
    // 회원 정보 저장 (회원가입)
    @PostMapping("/members")
    @ResponseBody
    public Long saveMember(@RequestBody final MemberRequest params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findMemberByLoginId(@PathVariable(value="loginId") final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@PathVariable(value="id" , required = false) final Long id, @RequestBody final MemberRequest params) {
        return memberService.updateMember(params);
    }

    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteMemberById(@PathVariable(value="id" , required = false) final Long id) {
        return memberService.deleteMemberById(id);
    }
    
    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberResponse login(HttpServletRequest request) {
    	
    	// 1. 회원 정보 조회
    	String loginId = request.getParameter("loginId");
    	String password = request.getParameter("password");
    	MemberResponse member = memberService.login(loginId, password);
    	
    	// 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
    	if (member != null) {
    		HttpSession session = request.getSession();
    		session.setAttribute("loginMember", member);
    		session.setMaxInactiveInterval(60 * 30);
    	}
    	
    	return member;
    	
    }
    
    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.do";
    }
    
    // 회원 수 카운팅 (ID 중복 체크)
    @GetMapping("/member-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam(value="loginId") final String loginId) {
        return memberService.countMemberByLoginId(loginId);
    }
    
    
    // 회원정보 수정 페이지
    @GetMapping("/member/updateMember.do")
    public String updateMember() {
        return "member/update";
    }
    
    // 회원정보 탈퇴 페이지
    @GetMapping("/member/deleteMember.do")
    public String deleteMember() {
        return "member/delete";
    }
    

}