package com.study.domain.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.domain.auth.vo.MenuVO;
import com.study.domain.auth.vo.UserInfoVO;

@Mapper
public interface AuthorizationMapper {

    List<MenuVO> getMenuRole(@Param("compId") int compId);

    List<Map<String, Object>> getRoleHierarchy();

    UserInfoVO getUserinfoById(String userId);
}
