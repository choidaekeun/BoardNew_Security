package com.study.user.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.user.user.dto.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO findByUserId(String userId);

    int updateUser(UserDTO params);

    int deleteUser(String userId);

}
