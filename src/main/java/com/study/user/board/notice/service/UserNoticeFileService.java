package com.study.user.board.notice.service;

import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;

import java.util.List;

public interface UserNoticeFileService {

    void saveFiles(Long boardId, List<FileRequest> files);

    /**
     * 파일 리스트 조회
     * @param boardId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    List<FileResponse> findAllFileByBoardId(Long boardId);

    /**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    List<FileResponse> findAllFileByIds(List<Long> ids);

    /**
     * 파일 삭제 (from Database)
     * @param ids - PK 리스트
     */
    void deleteAllFileByIds(List<Long> ids);

    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    FileResponse findFileById(Long id);
}
