package com.study.user.board.notice.dto;

import com.study.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeCommentSearchDto extends SearchDto {

    private Long boardId;
}
