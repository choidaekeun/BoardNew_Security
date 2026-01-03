package com.study.domain.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.common.dto.SearchDto;

/* 데이터베이스와의 통신을 위해 Mapper 인터페이스, 다른 SPRING에서는 DAO로 사용하기도 한다.
https://congsong.tistory.com/15
*/
@Mapper
public interface PostMapper {
	
	/**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<PostResponse> findAll(SearchDto params);

    /**
     * 게시글 조회수 불러오기
     * @return 게시글 수
     */
    int count(SearchDto params);
    
    /**
     * 게시글 수 카운팅
     * 
     */
    void hitCount(Long id);
    
    
	
	
}
