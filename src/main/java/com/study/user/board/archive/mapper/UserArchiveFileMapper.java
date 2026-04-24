package com.study.user.board.archive.mapper;

import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserArchiveFileMapper {

    void saveAll(List<FileRequest> files);
    List<FileResponse> findAllByBoardId(Long boardId);
    List<FileResponse> findAllByIds(List<Long> ids);
    void deleteAllByIds(List<Long> ids);
    FileResponse findById(Long id);
}
