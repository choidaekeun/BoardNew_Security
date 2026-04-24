package com.study.user.board.archive.service.impl;

import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveCommentRequest;
import com.study.user.board.archive.dto.ArchiveCommentResponse;
import com.study.user.board.archive.dto.ArchiveCommentSearchDto;
import com.study.user.board.archive.mapper.UserArchiveCommentMapper;
import com.study.user.board.archive.service.UserArchiveCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserArchiveCommentServiceImpl implements UserArchiveCommentService {

    private final UserArchiveCommentMapper commentMapper;

    @Transactional
    @Override
    public Long saveComment(ArchiveCommentRequest params) {
        commentMapper.save(params);
        return params.getCommentId();
    }

    @Override
    public ArchiveCommentResponse findCommentById(Long id) {
        return commentMapper.findById(id);
    }

    @Transactional
    @Override
    public Long updateComment(ArchiveCommentRequest params) {
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
    public PagingResponse<ArchiveCommentResponse> findAllComment(ArchiveCommentSearchDto params) {
        int count = commentMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        List<ArchiveCommentResponse> list = commentMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    @Transactional
    @Override
    public void deleteAllComment(List<String> ids) {
        commentMapper.deleteAll(ids);
    }
}
