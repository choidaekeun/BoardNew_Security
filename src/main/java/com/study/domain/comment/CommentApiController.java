package com.study.domain.comment;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.common.paging.PagingResponse;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final CommentService commentService;
	
    // 신규 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public CommentResponse saveComment(@PathVariable(value="postId") final Long postId, @RequestBody final CommentRequest params) {
    	Long id = commentService.saveComment(params);
    	return commentService.findCommentById(id);
    }   
    
    // 댓글 리스트 조회
    @GetMapping("/posts/{postId}/comments")
    public PagingResponse<CommentResponse> findAllComment(@PathVariable(value="postId") final Long postId, final CommentSearchDto params) {
        return commentService.findAllComment(params);
    }
    
    // 댓글 상세정보 조회
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentResponse findCommeentById(@PathVariable(value="postId") final Long postId, @PathVariable(value="id") final Long id) {
    	return commentService.findCommentById(id);
    }
    // 기존 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable(value="postId") final Long postId, @PathVariable(value="id") final Long id, @RequestBody final CommentRequest params) {
    	commentService.updateComment(params);
    	return commentService.findCommentById(id);
    }
    
    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable(value="postId") final Long postId, @PathVariable(value="id") final Long id) {
    	return commentService.deleteComment(id);
    }
    
    // 댓글 삭제
    @DeleteMapping("/posts/comments/")
    public Long deleteAllComment(@RequestBody final List<String> params) {
    	return commentService.deleteAllComment(params);
    }
    

}