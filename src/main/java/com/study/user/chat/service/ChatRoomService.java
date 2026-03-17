package com.study.user.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.user.chat.dto.ChatMessageDTO;
import com.study.user.chat.dto.ChatRoomDTO;
import com.study.user.chat.mapper.ChatMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatMapper chatMapper;
    private final PasswordEncoder passwordEncoder;

    public List<ChatRoomDTO> findAllRooms() {
        return chatMapper.findAllRooms();
    }

    public ChatRoomDTO findRoomById(String roomId) {
        return chatMapper.findRoomById(roomId);
    }

    public List<ChatMessageDTO> chatInfoList(String roomId) {
        return chatMapper.findMessagesByRoomId(roomId);
    }

    public ChatRoomDTO createChatRoomDTO(ChatRoomDTO params) {
        params.setRoomId(UUID.randomUUID().toString());

        if (Boolean.TRUE.equals(params.getSecretYn())) {
            params.encodingPassword(passwordEncoder);
        }

        chatMapper.saveRoom(params);
        return params;
    }

    public void addMessageToRoom(String roomId, ChatMessageDTO message) {
        chatMapper.saveMessage(message);
    }

    public Boolean secret(final String roomId, final String password) {
        ChatRoomDTO room = findRoomById(roomId);
        if (room == null) {
            return null;
        }

        String encodedPassword = room.getPassword();
        return passwordEncoder.matches(password, encodedPassword);
    }
}