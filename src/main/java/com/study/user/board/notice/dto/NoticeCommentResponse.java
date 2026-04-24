package com.study.user.board.notice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeCommentResponse {

    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
    private Boolean deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
