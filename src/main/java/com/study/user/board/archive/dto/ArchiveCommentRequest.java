package com.study.user.board.archive.dto;

import lombok.Getter;
import lombok.Setter;

//JSON 포맷으로 데이터를 주고받을때는 @Setter 사용하지 않아도 됨
@Getter
@Setter
public class ArchiveCommentRequest {
    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
}
