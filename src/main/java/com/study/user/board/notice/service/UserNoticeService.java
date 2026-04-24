package com.study.user.board.notice.service;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeRequest;
import com.study.user.board.notice.dto.NoticeResponse;

public interface UserNoticeService {

    Long saveNotice(NoticeRequest params);

    NoticeResponse findNoticeById(Long id);

    Long updateNotice(NoticeRequest params);

    Long deleteNotice(Long id);

    PagingResponse<NoticeResponse> findAllNotice(SearchDto params);

    void viewCount(Long id);

    Boolean secret(Long id, String password);
}
