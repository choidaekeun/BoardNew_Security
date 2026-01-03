package com.study.domain.post;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import com.study.domain.member.MemberResponse;

import lombok.RequiredArgsConstructor;


@Service  //해당 클래스가 비즈니스 로직을 담당하는 Service Layer의 클래스
@RequiredArgsConstructor
public class PostService {
	
	private final PostMapper postMapper;
	private final PasswordEncoder passwordEncoder;
	
	/**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostRequest params) {
    	params.encodingPassword(passwordEncoder);
        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResponse findPostById(final Long id) {
        return postMapper.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
    	params.encodingPassword(passwordEncoder);
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return 게시글 리스트
     */
    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
    	 // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = postMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination); // Pagination 자체에서 페이지 정보 저장하여 주석처리

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostResponse> list = postMapper.findAll(params);
        System.out.println("####type=> " + list.getClass().getName());
        return new PagingResponse<>(list, pagination);
    }
//    public List<PostResponse> findAllPost(final SearchDto params) {
//        return postMapper.findAll(params);
//    }
    
    public void viewCount(final Long id) {
    	postMapper.hitCount(id);
    	
    }
    
    /**
     * 비밀글
     * @param loginId - 비밀글 ID
     * @param password - 비밀번호
     * @return Boolean
     */
    public Boolean secret(final Long Id, final String password) {
 	   
 	   // 1. 회원 정보 및 비밀번호 조회
    	PostResponse post = findPostBySecretId(Id);
        String encodedPassword = (post == null) ? "" : post.getPassword();
        
        // 2. 회원 정보 및 비밀번호 체크
        Boolean	result = passwordEncoder.matches(password, encodedPassword);	
        if (post == null) {
            return null;
        }
        if (result == false) {
        	return false;
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        post.clearPassword();
        return result;
    }
    
    /**
     * 게시글 상세정보 조회
     * @param Id - UK
     * @return 게시글 상세정보
     */
    public PostResponse findPostBySecretId(Long Id) {
        return postMapper.findById(Id);
    }
    
    
	

}
