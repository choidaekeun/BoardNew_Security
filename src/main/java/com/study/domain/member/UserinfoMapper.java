package com.study.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserinfoMapper {
	
	UserInfoVO getUserinfoById(String userId);

}
