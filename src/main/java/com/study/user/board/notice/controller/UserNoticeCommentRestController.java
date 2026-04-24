package com.study.user.board.notice.controller;

import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeCommentRequest;
import com.study.user.board.notice.dto.NoticeCommentResponse;
import com.study.user.board.notice.dto.NoticeCommentSearchDto;
import com.study.user.board.notice.service.UserNoticeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserNoticeCommentRestController {

    private final UserNoticeCommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/notice/{boardId}/comments")
    public NoticeCommentResponse saveComment(@PathVariable final Long boardId, @RequestBody final NoticeCommentRequest params) {
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }

    // 댓글 리스트 조회
    @GetMapping("/notice/{boardId}/comments")
    public PagingResponse<NoticeCommentResponse> findAllComment(@PathVariable final Long boardId, final NoticeCommentSearchDto params) {
        return commentService.findAllComment(params);
    }

    // 댓글 상세정보 조회
    @GetMapping("/notice/{boardId}/comments/{id}")
    public NoticeCommentResponse findCommentById(@PathVariable final Long boardId, @PathVariable final Long id) {
        return commentService.findCommentById(id);
    }

    // 기존 댓글 수정
    @PatchMapping("/notice/{boardId}/comments/{id}")
    public NoticeCommentResponse updateComment(@PathVariable final Long boardId, @PathVariable final Long id, @RequestBody final NoticeCommentRequest params) {
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }

    // 댓글 삭제
    @DeleteMapping("/notice/{boardId}/comments/{id}")
    public Long deleteComment(@PathVariable final Long boardId, @PathVariable final Long id) {
        return commentService.deleteComment(id);
    }

    // 댓글 전체 삭제
    @DeleteMapping("/notice/comments")
    public Long deleteAllComment(@RequestBody final List<String> params) {
        return commentService.deleteAllComment(params);
    }
}
