package com.study.user.board.notice.dto;

import lombok.Getter;
import lombok.Setter;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeRequest {

    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;
    private List<MultipartFile> files = new ArrayList<>();
    private List<Long> removeFileIds = new ArrayList<>();
    private Boolean secretYn;
    private String password;

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        password = passwordEncoder.encode(password);
    }
}
