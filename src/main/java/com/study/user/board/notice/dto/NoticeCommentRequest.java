package com.study.user.board.notice.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeCommentRequest {

    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
}
