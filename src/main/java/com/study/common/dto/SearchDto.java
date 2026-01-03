package com.study.common.dto;

import com.study.common.paging.Pagination;
import lombok.Getter;
import lombok.Setter;
//DTO
@Getter
@Setter
public class SearchDto {

	private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String searchType;    // 검색 유형
    private Pagination pagination;

    public SearchDto() {	  // 초기화를 위한 생성자
    	this.page =1;
    	this.recordSize = 10;
    	this.pageSize = 10;
    }
// Pagination의 limitStart로 SearchDto의 offset을 대신
// 해당 페이지의 첫페이지 데이터 주소
//    public int getOffset() { 
//    	return (page - 1) * recordSize;
//    }
    
}
