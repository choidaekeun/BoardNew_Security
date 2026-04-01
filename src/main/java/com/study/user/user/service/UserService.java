package com.study.user.user.service;

import org.springframework.stereotype.Service;

import com.study.user.user.dto.UserDTO;
import com.study.user.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserDTO findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    public int updateUser(UserDTO params) {
        return userMapper.updateUser(params);
    }

    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }

}
