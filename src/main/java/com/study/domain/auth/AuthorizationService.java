package com.study.domain.auth;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.web.util.matcher.RequestMatcher;

public interface AuthorizationService {

    /**
     * 메뉴별 역할 조회
     * @param compId 회사 ID
     * @return 메뉴별 권한 목록
     */
    List<MenuVO> getMenuRole(int compId);

    /**
     * Role 계층 구조 조회
     * @return 역할 계층 경로 목록
     */
    List<Map<String, Object>> getRoleHierarchy();

    /**
     * DB에서 URL 패턴과 Role 매핑 맵 반환
     * @return LinkedHashMap<RequestMatcher, String>
     */
    LinkedHashMap<RequestMatcher, String> getResourceList();
}
