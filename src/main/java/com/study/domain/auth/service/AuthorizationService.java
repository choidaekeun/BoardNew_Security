package com.study.domain.auth.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.web.util.matcher.RequestMatcher;

import com.study.domain.auth.vo.MenuVO;
import com.study.domain.auth.vo.UserInfoVO;

public interface AuthorizationService {

    UserInfoVO getUserinfoById(String userId);

    List<MenuVO> getMenuRole(int compId);

    List<Map<String, Object>> getRoleHierarchy();

    LinkedHashMap<RequestMatcher, String> getResourceList();
}
