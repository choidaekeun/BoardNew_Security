package com.study.user.board.archive.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArchiveRequest {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;
    private List<MultipartFile> files = new ArrayList<>();
    private List<Long> removeFileIds = new ArrayList<>();
}
