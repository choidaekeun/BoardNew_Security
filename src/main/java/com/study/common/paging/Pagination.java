package com.study.common.paging;

import com.study.common.dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {

	private int totalRecordCount;     // 전체 데이터 수
	private int totalPageCount;       // 전체 페이지 수
	private int startPage;            // 첫 페이지 번호
	private int endPage;              // 끝 페이지 번호
	private int limitStart;           // LIMIT 시작 위치(주소)
	private boolean existPrevPage;    // 이전 페이지 존재 여부
	private boolean existNextPage;    // 다음 페이지 존재 여부
	
	public Pagination(int totalRecordCount, SearchDto params) { // 객체를 생성하는 시점에 페이지 정보를 계산
		System.out.println("####totalRecordCount=>" + totalRecordCount);
		if (totalRecordCount > 0 ) {
			this.totalRecordCount = totalRecordCount;
			calculation(params);
			params.setPagination(this); // params에 계산된 페이지 정보(this)를 저장, 기존 서비스에서 처리되던 로직이 Pagination 객체가 생성 시점에 실행
		}
	}
	
	private void calculation(SearchDto params) {
		
		// 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;
        System.out.println("####totalPageCount=>" + totalPageCount);
        
        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장 
        // page 번호 아무리 커도 전체 페이지수를 넘을수 없다.
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
        System.out.println("####startPage=>" + startPage);
        
        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;
        System.out.println("####endPage=>" + endPage);
        
        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        // 끝 페이지까지의 데이터가 존재하지 않기 떄문에 표기할 수 없다.
        if (endPage > totalPageCount) {
        	endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() - 1) * params.getRecordSize();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;
        
        // 다음 페이지 존재 여부 확인
        existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
        
        System.out.println("####limitStart existPrevPage existNextPage=>" + limitStart +"/"+ existPrevPage +"/"+ existNextPage);
    }
		
}
