package com.study.admin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.admin.user.dto.AdminUserDTO;
import com.study.common.dto.SearchDto;

@Mapper
public interface AdminUserMapper {

    List<AdminUserDTO> findAll(SearchDto params);

    int count(SearchDto params);

    int deleteByUserIds(List<String> userIds);

}