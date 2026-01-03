package com.study.domain.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{
	
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
