package com.study.user.board.archive.mapper;

import com.study.common.dto.SearchDto;
import com.study.user.board.archive.dto.ArchiveRequest;
import com.study.user.board.archive.dto.ArchiveResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserArchiveMapper {

    void save(ArchiveRequest params);
    ArchiveResponse findById(Long id);
    void update(ArchiveRequest params);
    void deleteById(Long id);
    List<ArchiveResponse> findAll(SearchDto params);
    int count(SearchDto params);
    void hitCount(Long id);
}
