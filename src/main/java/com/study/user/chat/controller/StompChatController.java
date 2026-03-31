package com.study.user.chat.controller;

import lombok.RequiredArgsConstructor;
import com.study.user.chat.dto.ChatMemberDTO;
import com.study.user.chat.dto.ChatMessageDTO;
import com.study.user.chat.service.ChatRoomService;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final ChatRoomService chatRoomService;

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message, Principal principal){
    	// 참여자 저장
    	ChatMemberDTO member = new ChatMemberDTO();
    	member.setRoomId(message.getRoomId());
    	member.setRegId(principal.getName());
    	member.setWriter(message.getWriter());
    	chatRoomService.joinRoom(member);

    	message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message, Principal principal){
        String userId = principal.getName();
    	chatRoomService.addMessageToRoom(message.getRoomId(), message, userId);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/leave")
    public void leave(ChatMessageDTO message, Principal principal){
        ChatMemberDTO member = new ChatMemberDTO();
        member.setRoomId(message.getRoomId());
        member.setRegId(principal.getName());
        chatRoomService.leaveRoom(member);

        message.setMessage(message.getWriter() + "님이 채팅방에 나갔습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
