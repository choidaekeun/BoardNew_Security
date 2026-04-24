package com.study.user.board.archive.mapper;

import com.study.user.board.archive.dto.ArchiveCommentRequest;
import com.study.user.board.archive.dto.ArchiveCommentResponse;
import com.study.user.board.archive.dto.ArchiveCommentSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserArchiveCommentMapper {

    void save(ArchiveCommentRequest params);
    ArchiveCommentResponse findById(Long id);
    void update(ArchiveCommentRequest params);
    void deleteById(Long id);
    List<ArchiveCommentResponse> findAll(ArchiveCommentSearchDto params);
    int count(ArchiveCommentSearchDto params);
    void deleteAll(List<String> ids);
}
