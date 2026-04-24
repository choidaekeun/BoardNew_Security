package com.study.user.board.notice.controller;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.file.FileUtils;
import com.study.common.file.dto.FileRequest;
import com.study.common.file.dto.FileResponse;
import com.study.user.board.notice.dto.NoticeRequest;
import com.study.user.board.notice.service.UserNoticeFileService;
import com.study.user.board.notice.service.UserNoticeService;
import jakarta.servlet.http.HttpServletRequest;
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
public class UserNoticeRestController {

    private final UserNoticeService noticeService;
    private final UserNoticeFileService noticeFileService;
    private final FileUtils fileUtils;

    @PostMapping("/notice/save")
    public String saveNotice(final NoticeRequest params, Model model) {
        Long id = noticeService.saveNotice(params);
        List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());
        noticeFileService.saveFiles(id, files);
        MessageDto message = new MessageDto("공지사항 생성이 완료되었습니다.", "/user/board/notice", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/notice/update")
    public String updateNotice(final NoticeRequest params, final SearchDto queryParams, Model model) {
        noticeService.updateNotice(params);
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());
        noticeFileService.saveFiles(params.getBoardId(), uploadFiles);
        List<FileResponse> deleteFiles = noticeFileService.findAllFileByIds(params.getRemoveFileIds());
        fileUtils.deleteFiles(deleteFiles);
        noticeFileService.deleteAllFileByIds(params.getRemoveFileIds());
        MessageDto message = new MessageDto("공지사항 수정이 완료되었습니다.", "/user/board/notice", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/notice/delete")
    public String deleteNotice(@RequestParam(value = "id") final Long id, final SearchDto queryParams, Model model) {
        noticeService.deleteNotice(id);
        MessageDto message = new MessageDto("공지사항 삭제가 완료되었습니다.", "/user/board/notice", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/notice/secretCheck")
    @ResponseBody
    public Boolean secretNotice(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("Id"));
        String password = request.getParameter("password");
        return noticeService.secret(id, password);
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
