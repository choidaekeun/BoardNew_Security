package com.study.controller;

import lombok.RequiredArgsConstructor;
import com.study.common.dto.ChatMessageDTO;
import com.study.common.repository.ChatRoomRepository;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final ChatRoomRepository repository;

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
    	
    	List<ChatMessageDTO> chatMessages = repository.chatInfoList(message.getRoomId());

    	template.convertAndSend("/sub/chat/room/" + message.getRoomId(), chatMessages);
    	
    	message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
    	repository.addMessageToRoom(message.getRoomId(), message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
