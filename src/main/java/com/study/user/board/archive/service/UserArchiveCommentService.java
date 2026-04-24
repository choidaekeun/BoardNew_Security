package com.study.user.board.archive.service;

import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveCommentRequest;
import com.study.user.board.archive.dto.ArchiveCommentResponse;
import com.study.user.board.archive.dto.ArchiveCommentSearchDto;

import java.util.List;

public interface UserArchiveCommentService {

    Long saveComment(ArchiveCommentRequest params);
    ArchiveCommentResponse findCommentById(Long id);
    Long updateComment(ArchiveCommentRequest params);
    Long deleteComment(Long id);
    PagingResponse<ArchiveCommentResponse> findAllComment(ArchiveCommentSearchDto params);
    void deleteAllComment(List<String> ids);
}
