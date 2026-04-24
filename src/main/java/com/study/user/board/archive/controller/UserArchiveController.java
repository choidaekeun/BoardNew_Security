package com.study.user.board.archive.controller;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.user.board.archive.dto.ArchiveResponse;
import com.study.user.board.archive.service.UserArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/board")
@RequiredArgsConstructor
public class UserArchiveController {

    private final UserArchiveService archiveService;

    @GetMapping("/archive")
    public String openArchiveList(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<ArchiveResponse> response = archiveService.findAllArchive(params);
        model.addAttribute("response", response);
        return "/user/board/archive/archive";
    }

    @GetMapping("/archive/write")
    public String openArchiveWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            ArchiveResponse archive = archiveService.findArchiveById(id);
            model.addAttribute("archive", archive);
        }
        return "/user/board/archive/archivewrite";
    }

    @GetMapping("/archive/view")
    public String openArchiveView(@RequestParam(value = "id") final Long id, Model model) {
        ArchiveResponse archive = archiveService.findArchiveById(id);
        archiveService.viewCount(id);
        model.addAttribute("archive", archive);
        return "/user/board/archive/archiveview";
    }
}
