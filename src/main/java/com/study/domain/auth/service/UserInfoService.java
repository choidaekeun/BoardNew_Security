package com.study.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.domain.auth.vo.UserInfoVO;

public interface UserInfoService {

	public UserInfoVO getUserinfoById(String userId);

	public PasswordEncoder passwordEncoder();
}
