package com.study.user.board.archive.controller;

import com.study.common.file.FileUtils;
import com.study.common.file.dto.FileResponse;
import com.study.user.board.archive.service.UserArchiveFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserArchiveFileRestController {

    private final UserArchiveFileService archiveFileService;
    private final FileUtils fileUtils;

    @GetMapping("/archive/{boardId}/files")
    public List<FileResponse> findAllFileByBoardId(@PathVariable final Long boardId) {
        return archiveFileService.findAllFileByBoardId(boardId);
    }

    @GetMapping("/archive/{boardId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable final Long boardId,
            @PathVariable final Long fileId) {
        FileResponse file = archiveFileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file.getCreatedDate(), file.getSaveName());
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
