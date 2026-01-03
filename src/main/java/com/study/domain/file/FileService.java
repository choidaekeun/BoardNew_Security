package com.study.domain.file;

import java.util.Collections;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    @Transactional
    public void saveFiles(final Long postId, final List<FileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setPostId(postId);
        }
        fileMapper.saveAll(files);
    }
    
    /**
     * 파일 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByPostId(final Long postId) {
        return fileMapper.findAllByPostId(postId);
    }

    /**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList(); // ids가 비어있는 경우, 사이즈가 0인 비어있는 리스트를 리턴
        }
        return fileMapper.findAllByIds(ids); //ids가 비어있지 않을 때 쿼리를 실행
    }

    /**
     * 파일 삭제 (from Database)
     * @param ids - PK 리스트
     */
    @Transactional
    public void deleteAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        fileMapper.deleteAllByIds(ids); //ids가 비어있지 않은 경우에만 쿼리를 실행
    }
    
    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    public FileResponse findFileById(final Long id) {
    	return fileMapper.findById(id);
    }

}
