package com.study.domain.auth.service.impl;

import com.study.domain.auth.mapper.UserinfoMapper;
import com.study.domain.auth.service.UserInfoService;
import com.study.domain.auth.vo.UserInfoVO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
    private UserinfoMapper mapper;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfoVO getUserinfoById(String userId){
        return mapper.getUserinfoById(userId);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

}