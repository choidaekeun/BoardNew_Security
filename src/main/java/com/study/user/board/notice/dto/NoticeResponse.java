package com.study.user.board.notice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeResponse {

    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private Boolean noticeYn;
    private Boolean deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Boolean secretYn;
    private String password;

    public void clearPassword() {
        this.password = "";
    }
}
