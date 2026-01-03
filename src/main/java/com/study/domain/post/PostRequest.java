package com.study.domain.post;

import lombok.Getter;
import lombok.Setter;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

//DTO
@Getter
@Setter
public class PostRequest {
	
	private Long id;             // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private Boolean noticeYn;    // 공지글 여부
    private List<MultipartFile> files = new ArrayList<>();	// 첨부파일 List
    private List<Long> removeFileIds = new ArrayList<>();	// 삭제할 첨부파일 id List
    private Boolean secretYn;
    private String password;
    
    public void encodingPassword(PasswordEncoder passwordEncoder) {
    	if (StringUtils.isEmpty(password)) {
    		return;
    	}
    	password = passwordEncoder.encode(password);
    }
}
