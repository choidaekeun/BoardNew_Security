package com.study.user.board.notice.service;

import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeCommentRequest;
import com.study.user.board.notice.dto.NoticeCommentResponse;
import com.study.user.board.notice.dto.NoticeCommentSearchDto;

import java.util.List;

public interface UserNoticeCommentService {

    // 신규 댓글 생성
    Long saveComment(NoticeCommentRequest params);

    // 댓글 상세정보 조회
    NoticeCommentResponse findCommentById(Long id);

    // 기존 댓글 수정
    Long updateComment(NoticeCommentRequest params);

    // 댓글 삭제
    Long deleteComment(Long id);

    // 댓글 리스트 조회
    PagingResponse<NoticeCommentResponse> findAllComment(NoticeCommentSearchDto params);

    // 댓글 전체 삭제
    Long deleteAllComment(List<String> params);
}
