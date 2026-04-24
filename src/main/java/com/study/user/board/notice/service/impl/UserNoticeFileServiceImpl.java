package com.study.user.board.notice.service.impl;

import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;
import com.study.user.board.notice.mapper.UserNoticeFileMapper;
import com.study.user.board.notice.service.UserNoticeFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNoticeFileServiceImpl implements UserNoticeFileService {

    private final UserNoticeFileMapper noticeFileMapper;

    @Transactional
    @Override
    public void saveFiles(Long boardId, List<FileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setBoardId(boardId);
        }
        noticeFileMapper.saveAll(files);
    }

    @Override
    public List<FileResponse> findAllFileByBoardId(Long boardId) {
        return noticeFileMapper.findAllByBoardId(boardId);
    }

    @Override
    public List<FileResponse> findAllFileByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return noticeFileMapper.findAllByIds(ids);
    }

    @Transactional
    @Override
    public void deleteAllFileByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        noticeFileMapper.deleteAllByIds(ids);
    }

    @Override
    public FileResponse findFileById(Long id) {
        return noticeFileMapper.findById(id);
    }
}
