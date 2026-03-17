package com.study.user.chat.controller;

import lombok.RequiredArgsConstructor;
import com.study.user.chat.dto.ChatMessageDTO;
import com.study.user.chat.service.ChatRoomService;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final ChatRoomService chatRoomService;

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
        // 기존 채팅 히스토리
    	List<ChatMessageDTO> chatMessages = chatRoomService.chatInfoList(message.getRoomId());

    	template.convertAndSend("/sub/chat/room/" + message.getRoomId(), chatMessages);

    	message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
    	chatRoomService.addMessageToRoom(message.getRoomId(), message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
