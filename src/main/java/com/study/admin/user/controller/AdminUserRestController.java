package com.study.admin.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.study.admin.user.dto.AdminUserDTO;
import com.study.admin.user.service.AdminUserService;
import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserRestController {

    private final AdminUserService adminUserService;

    // 회원 목록 조회
    @GetMapping("/users")
    public PagingResponse<AdminUserDTO> findAllUser(@ModelAttribute SearchDto params) {
        return adminUserService.findAllUser(params);
    }

    // 회원 삭제
    @DeleteMapping("/users")
    public int deleteUsers(@RequestBody List<String> userIds) {
        return adminUserService.deleteUsers(userIds);
    }

}