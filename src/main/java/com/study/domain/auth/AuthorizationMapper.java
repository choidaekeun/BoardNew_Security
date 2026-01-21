package com.study.domain.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorizationMapper {

    /**
     * 메뉴별 역할 조회 (URL-Role 매핑)
     * @param compId 회사 ID
     * @return 메뉴별 권한 목록
     */
    List<MenuVO> getMenuRole(@Param("compId") int compId);

    /**
     * Role 계층 구조 조회 (path 문자열 반환)
     * @return 역할 계층 경로 목록
     */
    List<Map<String, Object>> getRoleHierarchy();
}
