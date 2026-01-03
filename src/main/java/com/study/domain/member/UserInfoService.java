package com.study.domain.member;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserInfoService {

	public UserInfoVO getUserinfoById(String userId);
	
	public PasswordEncoder passwordEncoder();
}
