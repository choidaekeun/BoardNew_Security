package com.study.user.board.archive.controller;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.file.FileUtils;
import com.study.common.file.dto.FileResponse;
import com.study.common.file.dto.FileRequest;
import com.study.user.board.archive.dto.ArchiveRequest;
import com.study.user.board.archive.service.UserArchiveFileService;
import com.study.user.board.archive.service.UserArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserArchiveRestController {

    private final UserArchiveService archiveService;
    private final UserArchiveFileService archiveFileService;
    private final FileUtils fileUtils;

    @PostMapping("/archive/save")
    public String saveArchive(final ArchiveRequest params, Model model) {
        Long id = archiveService.saveArchive(params);
        List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());
        archiveFileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/user/board/archive", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/archive/update")
    public String updateArchive(final ArchiveRequest params, final SearchDto queryParams, Model model) {
        archiveService.updateArchive(params);

        List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());
        archiveFileService.saveFiles(params.getBoardId(), uploadFiles);

        List<FileResponse> deleteFiles = archiveFileService.findAllFileByIds(params.getRemoveFileIds());
        for (FileResponse file : deleteFiles) {
            fileUtils.deleteFileByInfo(file.getCreatedDate(), file.getSaveName());
        }
        archiveFileService.deleteAllFileByIds(params.getRemoveFileIds());

        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/user/board/archive", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/archive/delete")
    public String deleteArchive(@RequestParam(value = "id") final Long id, final SearchDto queryParams, Model model) {
        archiveService.deleteArchive(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/user/board/archive", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "/common/messageRedirect";
    }

    private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }
}
