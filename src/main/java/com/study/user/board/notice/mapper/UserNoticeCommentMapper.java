package com.study.user.board.notice.mapper;

import com.study.user.board.notice.dto.NoticeCommentRequest;
import com.study.user.board.notice.dto.NoticeCommentResponse;
import com.study.user.board.notice.dto.NoticeCommentSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserNoticeCommentMapper {

    /**
      * 댓글 저장
      * @param params - 댓글 정보
    */
    void save(NoticeCommentRequest params);

    /**
      * 댓글 상세정보 조회
      * @param id - PK
      * @return 댓글 상세정보
    */
    NoticeCommentResponse findById(Long id);

    /**
      * 댓글 수정
      * @param params - 댓글 정보
    */
    void update(NoticeCommentRequest params);

    /**
      * 댓글 삭제
      * @param id - PK
    */
    void deleteById(Long id);

    /**
        * 댓글 리스트 조회
        * @param params - search conditions
        * @return 댓글 리스트
    */
    List<NoticeCommentResponse> findAll(NoticeCommentSearchDto params);

    /**
        * 댓글 수 카운팅
        * @param params - search conditions
        * @return 댓글 수
    */
    int count(NoticeCommentSearchDto params);

    Long deleteByAll(List<String> params);
}
