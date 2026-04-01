package com.study.admin.user.service;

import java.util.List;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.admin.user.dto.AdminUserDTO;

public interface AdminUserService {

    PagingResponse<AdminUserDTO> findAllUser(SearchDto params);

    int deleteUsers(List<String> userIds);

}