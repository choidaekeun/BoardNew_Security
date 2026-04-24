package com.study.user.board.notice.service.impl;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeRequest;
import com.study.user.board.notice.dto.NoticeResponse;
import com.study.user.board.notice.mapper.UserNoticeMapper;
import com.study.user.board.notice.service.UserNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNoticeServiceImpl implements UserNoticeService {

    private final UserNoticeMapper noticeMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long saveNotice(NoticeRequest params) {
        params.encodingPassword(passwordEncoder);
        noticeMapper.save(params);
        return params.getBoardId();
    }

    @Override
    public NoticeResponse findNoticeById(Long id) {
        return noticeMapper.findById(id);
    }

    @Transactional
    @Override
    public Long updateNotice(NoticeRequest params) {
        params.encodingPassword(passwordEncoder);
        noticeMapper.update(params);
        return params.getBoardId();
    }

    @Transactional
    @Override
    public Long deleteNotice(Long id) {
        noticeMapper.deleteById(id);
        return id;
    }

    @Override
    public PagingResponse<NoticeResponse> findAllNotice(SearchDto params) {
        int count = noticeMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        List<NoticeResponse> list = noticeMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    @Override
    public void viewCount(Long id) {
        noticeMapper.hitCount(id);
    }

    @Override
    public Boolean secret(Long id, String password) {
        NoticeResponse notice = noticeMapper.findById(id);
        String encodedPassword = (notice == null) ? "" : notice.getPassword();
        Boolean result = passwordEncoder.matches(password, encodedPassword);
        if (notice == null) {
            return null;
        }
        if (!result) {
            return false;
        }
        notice.clearPassword();
        return result;
    }
}
