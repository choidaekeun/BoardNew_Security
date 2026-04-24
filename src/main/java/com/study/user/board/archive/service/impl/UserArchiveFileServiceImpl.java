package com.study.user.board.archive.service.impl;

import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;
import com.study.user.board.archive.mapper.UserArchiveFileMapper;
import com.study.user.board.archive.service.UserArchiveFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserArchiveFileServiceImpl implements UserArchiveFileService {

    private final UserArchiveFileMapper archiveFileMapper;

    @Transactional
    @Override
    public void saveFiles(Long boardId, List<FileRequest> fileRequests) {
        if (CollectionUtils.isEmpty(fileRequests)) {
            return;
        }
        for (FileRequest file : fileRequests) {
            file.setBoardId(boardId);
        }
        archiveFileMapper.saveAll(fileRequests);
    }

    @Override
    public List<FileResponse> findAllFileByBoardId(Long boardId) {
        return archiveFileMapper.findAllByBoardId(boardId);
    }

    @Override
    public List<FileResponse> findAllFileByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return archiveFileMapper.findAllByIds(ids);
    }

    @Transactional
    @Override
    public void deleteAllFileByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        archiveFileMapper.deleteAllByIds(ids);
    }

    @Override
    public FileResponse findFileById(Long id) {
        return archiveFileMapper.findById(id);
    }
}
