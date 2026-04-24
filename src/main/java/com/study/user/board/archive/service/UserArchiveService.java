package com.study.user.board.archive.service;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveRequest;
import com.study.user.board.archive.dto.ArchiveResponse;

public interface UserArchiveService {

    Long saveArchive(ArchiveRequest params);
    ArchiveResponse findArchiveById(Long id);
    Long updateArchive(ArchiveRequest params);
    Long deleteArchive(Long id);
    PagingResponse<ArchiveResponse> findAllArchive(SearchDto params);
    void viewCount(Long id);
}
