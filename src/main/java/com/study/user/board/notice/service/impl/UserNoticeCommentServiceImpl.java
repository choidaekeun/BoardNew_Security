package com.study.user.board.notice.service.impl;

import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeCommentRequest;
import com.study.user.board.notice.dto.NoticeCommentResponse;
import com.study.user.board.notice.dto.NoticeCommentSearchDto;
import com.study.user.board.notice.mapper.UserNoticeCommentMapper;
import com.study.user.board.notice.service.UserNoticeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNoticeCommentServiceImpl implements UserNoticeCommentService {

    private final UserNoticeCommentMapper commentMapper;

    @Transactional
    @Override
    public Long saveComment(NoticeCommentRequest params) {
        commentMapper.save(params);
        return params.getCommentId();
    }

    @Override
    public NoticeCommentResponse findCommentById(Long id) {
        return commentMapper.findById(id);
    }

    @Transactional
    @Override
    public Long updateComment(NoticeCommentRequest params) {
        commentMapper.update(params);
        return params.getCommentId();
    }

    @Transactional
    @Override
    public Long deleteComment(Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    @Override
    public PagingResponse<NoticeCommentResponse> findAllComment(NoticeCommentSearchDto params) {
        int count = commentMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        List<NoticeCommentResponse> list = commentMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    @Transactional
    @Override
    public Long deleteAllComment(List<String> params) {
        return commentMapper.deleteByAll(params);
    }
}
