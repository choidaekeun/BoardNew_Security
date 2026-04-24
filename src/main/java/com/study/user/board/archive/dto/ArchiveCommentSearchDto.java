package com.study.user.board.archive.dto;

import com.study.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchiveCommentSearchDto extends SearchDto {
    private Long boardId;
}
