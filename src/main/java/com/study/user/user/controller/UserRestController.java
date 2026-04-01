package com.study.user.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.study.domain.auth.vo.UserInfoVO;
import com.study.user.user.dto.UserDTO;
import com.study.user.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/user")
public class UserRestController {

    private final UserService userService;

    // 회원 상세정보 조회
    @GetMapping("/{userId}")
    public UserDTO findByUserId(@PathVariable String userId) {
        return userService.findByUserId(userId);
    }

    // 회원 정보 수정
    @PatchMapping("/{userId}")
    public int updateUser(@PathVariable String userId, @RequestBody UserDTO params) {
        params.setUserId(userId);
        int result = userService.updateUser(params);
        //세션 정보 수정
        UserInfoVO principal = (UserInfoVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        principal.setUserNm(params.getUserNm());
        return result;
    }

    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/{userId}")
    public int deleteUser(@PathVariable String userId, HttpSession session) {
        int result = userService.deleteUser(userId);
        if (result > 0) {
            session.invalidate();
            SecurityContextHolder.clearContext();
        }
        return result;
    }

}
