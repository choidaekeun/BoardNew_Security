package com.study.user.board.archive.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArchiveCommentResponse {
    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
    private Boolean deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
