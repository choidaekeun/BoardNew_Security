package com.study.domain.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.domain.auth.vo.UserInfoVO;

@Mapper
public interface UserinfoMapper {

	UserInfoVO getUserinfoById(String userId);

}
