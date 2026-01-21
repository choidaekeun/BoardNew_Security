package com.study.domain.file;

import com.study.common.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;
    private final FileUtils fileUtils;

    // 파일 리스트 조회
    @GetMapping("/posts/{postId}/files")
    public List<FileResponse> findAllFileByPostId(@PathVariable(value="postId") final Long postId) {
    	List<FileResponse> filelist = fileService.findAllFileByPostId(postId);
    	System.out.println("####test");
    	return filelist;
    }
    
    // 첨부파일 다운로드
    @GetMapping("/posts/{postId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable(value="postId") final Long postId, @PathVariable(value="fileId") final Long fileId) {
    	FileResponse file = fileService.findFileById(fileId);
    	Resource resource = fileUtils.readFileAsResource(file);
    	try {
    		String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
    		return ResponseEntity.ok()
		    		.contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";") //파일명 저장
		            .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
		            .body(resource);
    		
    	} catch (UnsupportedEncodingException e) {
    		throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
    	}
    }
    

}