package com.study.user.board.notice.controller;

import com.study.common.file.FileUtils;
import com.study.common.file.dto.FileResponse;
import com.study.user.board.notice.service.UserNoticeFileService;
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
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserNoticeFileRestController {

    private final UserNoticeFileService noticeFileService;
    private final FileUtils fileUtils;

    // 파일 리스트 조회
    @GetMapping("/notice/{boardId}/files")
    public List<FileResponse> findAllFileByBoardId(@PathVariable final Long boardId) {
        return noticeFileService.findAllFileByBoardId(boardId);
    }

    // 첨부파일 다운로드
    @GetMapping("/notice/{boardId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final Long boardId, @PathVariable final Long fileId) {
        FileResponse file = noticeFileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }
}
