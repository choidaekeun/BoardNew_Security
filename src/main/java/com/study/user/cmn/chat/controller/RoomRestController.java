package com.study.user.cmn.chat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.common.CommonController;
import com.study.user.cmn.chat.dto.ChatMemberDTO;
import com.study.user.cmn.chat.dto.ChatMessageDTO;
import com.study.user.cmn.chat.service.ChatRoomService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/cmn/chat")
public class RoomRestController extends CommonController {

    private final ChatRoomService repository;

    // 채팅 히스토리 조회
    @GetMapping("/messages")
    public List<ChatMessageDTO> getMessages(@RequestParam String roomId) {
        return repository.chatInfoList(roomId);
    }

    // 채팅방 참여 이력 조회
    @GetMapping("/member")
    public ChatMemberDTO getMember(@RequestParam String roomId) {
        return repository.findMember(roomId, getUserId());
    }

    // 비밀방 체크
    @PostMapping("/checkpassword")
    public Boolean checkPassword(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String roomId = request.getParameter("roomId");
        String password = request.getParameter("password");
        return repository.secret(roomId, password);
    }
}
