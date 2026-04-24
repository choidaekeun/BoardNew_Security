package com.study.user.board.archive.service.impl;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveRequest;
import com.study.user.board.archive.dto.ArchiveResponse;
import com.study.user.board.archive.mapper.UserArchiveMapper;
import com.study.user.board.archive.service.UserArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserArchiveServiceImpl implements UserArchiveService {

    private final UserArchiveMapper archiveMapper;

    @Transactional
    @Override
    public Long saveArchive(ArchiveRequest params) {
        archiveMapper.save(params);
        return params.getBoardId();
    }

    @Override
    public ArchiveResponse findArchiveById(Long id) {
        return archiveMapper.findById(id);
    }

    @Transactional
    @Override
    public Long updateArchive(ArchiveRequest params) {
        archiveMapper.update(params);
        return params.getBoardId();
    }

    @Override
    public Long deleteArchive(Long id) {
        archiveMapper.deleteById(id);
        return id;
    }

    @Override
    public PagingResponse<ArchiveResponse> findAllArchive(SearchDto params) {
        int count = archiveMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        List<ArchiveResponse> list = archiveMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    @Override
    public void viewCount(Long id) {
        archiveMapper.hitCount(id);
    }
}
