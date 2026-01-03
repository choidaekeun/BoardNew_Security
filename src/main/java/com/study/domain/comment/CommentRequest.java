package com.study.domain.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JSON 포맷으로 데이터를 주고받기에 @Setter 어노테이션은 필요하지 않음
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class CommentRequest {
	
	private Long id;           // 댓글 번호 (PK)
    private Long postId;       // 게시글 번호 (FK)
    private String content;    // 내용
    private String writer;     // 작성자

}
