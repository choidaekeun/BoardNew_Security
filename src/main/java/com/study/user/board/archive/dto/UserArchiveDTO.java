package com.study.user.board.archive.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserArchiveDTO {

    @Getter
    @Setter
    public static class ArchiveRequest {
        private Long boardId;
        private String title;
        private String content;
        private String writer;
        private Boolean noticeYn;
        private List<MultipartFile> files = new ArrayList<>();
        private List<Long> removeFileIds = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class ArchiveResponse {
        private Long boardId;
        private String title;
        private String content;
        private String writer;
        private int viewCnt;
        private Boolean noticeYn;
        private Boolean deleteYn;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}
