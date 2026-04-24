package com.study.user.board.archive.controller;

import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveCommentRequest;
import com.study.user.board.archive.dto.ArchiveCommentResponse;
import com.study.user.board.archive.dto.ArchiveCommentSearchDto;
import com.study.user.board.archive.service.UserArchiveCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserArchiveCommentRestController {

    private final UserArchiveCommentService commentService;

    @PostMapping("/archive/{boardId}/comments")
    public ArchiveCommentResponse saveComment(
            @PathVariable final Long boardId,
            @RequestBody final ArchiveCommentRequest params) {
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }

    @GetMapping("/archive/{boardId}/comments")
    public PagingResponse<ArchiveCommentResponse> findAllComment(
            @PathVariable final Long boardId,
            final ArchiveCommentSearchDto params) {
        return commentService.findAllComment(params);
    }

    @GetMapping("/archive/{boardId}/comments/{id}")
    public ArchiveCommentResponse findCommentById(
            @PathVariable final Long boardId,
            @PathVariable final Long id) {
        return commentService.findCommentById(id);
    }

    @PatchMapping("/archive/{boardId}/comments/{id}")
    public ArchiveCommentResponse updateComment(
            @PathVariable final Long boardId,
            @PathVariable final Long id,
            @RequestBody final ArchiveCommentRequest params) {
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }

    @DeleteMapping("/archive/{boardId}/comments/{id}")
    public Long deleteComment(
            @PathVariable final Long boardId,
            @PathVariable final Long id) {
        return commentService.deleteComment(id);
    }

    @DeleteMapping("/archive/comments/")
    public void deleteAllComment(@RequestBody final List<String> ids) {
        commentService.deleteAllComment(ids);
    }
}
