package com.study.admin.user.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.admin.user.dto.AdminUserDTO;
import com.study.admin.user.mapper.AdminUserMapper;
import com.study.admin.user.service.AdminUserService;
import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserMapper adminUserMapper;

    @Override
    public PagingResponse<AdminUserDTO> findAllUser(SearchDto params) {
        int count = adminUserMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<AdminUserDTO> list = adminUserMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    @Override
    @Transactional
    public int deleteUsers(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return 0;
        }
        return adminUserMapper.deleteByUserIds(userIds);
    }

}
