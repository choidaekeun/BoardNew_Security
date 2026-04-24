package com.study.user.board.notice.mapper;

import com.study.common.dto.SearchDto;
import com.study.user.board.notice.dto.NoticeRequest;
import com.study.user.board.notice.dto.NoticeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserNoticeMapper {

    void save(NoticeRequest params);

    NoticeResponse findById(Long id);

    void update(NoticeRequest params);

    void deleteById(Long id);

    List<NoticeResponse> findAll(SearchDto params);

    int count(SearchDto params);

    void hitCount(Long id);
}
