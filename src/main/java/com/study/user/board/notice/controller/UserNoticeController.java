package com.study.user.board.notice.controller;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.user.board.notice.dto.NoticeResponse;
import com.study.user.board.notice.service.UserNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserNoticeController {

    private final UserNoticeService noticeService;

    @GetMapping("/notice")
    public String openNoticeList(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<NoticeResponse> response = noticeService.findAllNotice(params);
        model.addAttribute("response", response);
        return "/user/board/notice/notice";
    }

    @GetMapping("/notice/write")
    public String openNoticeWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            NoticeResponse notice = noticeService.findNoticeById(id);
            model.addAttribute("notice", notice);
        }
    return "/user/board/notice/noticewrite";
    }

    @GetMapping("/notice/view")
    public String openNoticeView(@RequestParam(value = "id") final Long id, Model model) {
        NoticeResponse notice = noticeService.findNoticeById(id);
        noticeService.viewCount(id);
        model.addAttribute("notice", notice);
        return "/user/board/notice/noticeview";
    }

}
