package com.study.domain.auth.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.study.domain.auth.mapper.AuthorizationMapper;
import com.study.domain.auth.service.AuthorizationService;
import com.study.domain.auth.vo.MenuVO;
import com.study.domain.auth.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationMapper authorizationMapper;

    @Override
    public UserInfoVO getUserinfoById(String userId) {
        return authorizationMapper.getUserinfoById(userId);
    }

    @Override
    public List<MenuVO> getMenuRole(int compId) {
        return authorizationMapper.getMenuRole(compId);
    }

    @Override
    public List<Map<String, Object>> getRoleHierarchy() {
        return authorizationMapper.getRoleHierarchy();
    }

    @Override
    public LinkedHashMap<RequestMatcher, String> getResourceList() {
        LinkedHashMap<RequestMatcher, String> result = new LinkedHashMap<>();
        List<MenuVO> resourcesList = authorizationMapper.getMenuRole(0);

        resourcesList.forEach(menu -> {
            result.put(
                new AntPathRequestMatcher(menu.getMenuLc() + "/**"),
                menu.getRoleSecurity()
            );
        });

        return result;
    }
}
