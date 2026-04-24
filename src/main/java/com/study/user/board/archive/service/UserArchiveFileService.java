package com.study.user.board.archive.service;

import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;

import java.util.List;

public interface UserArchiveFileService {

    void saveFiles(Long boardId, List<FileRequest> files);
    List<FileResponse> findAllFileByBoardId(Long boardId);
    List<FileResponse> findAllFileByIds(List<Long> ids);
    void deleteAllFileByIds(List<Long> ids);
    FileResponse findFileById(Long id);
}
