package com.study.user.board.archive.dto;

import com.study.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ArchiveCommentDTO {

    @Getter
    @Setter
    public static class CommentRequest {
        private Long commentId;
        private Long boardId;
        private String content;
        private String writer;
    }

    @Getter
    @Setter
    public static class CommentResponse {
        private Long commentId;
        private Long boardId;
        private String content;
        private String writer;
        private Boolean deleteYn;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }

    @Getter
    @Setter
    public static class CommentSearchDto extends SearchDto {
        private Long boardId;
    }
}
