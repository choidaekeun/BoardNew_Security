package com.study.user.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.user.chat.dto.ChatMemberDTO;
import com.study.user.chat.dto.ChatMessageDTO;
import com.study.user.chat.dto.ChatRoomDTO;

@Mapper
public interface ChatMapper {

    List<ChatRoomDTO> findAllRooms();

    ChatRoomDTO findRoomById(String roomId);

    void saveRoom(ChatRoomDTO room);

    List<ChatMessageDTO> findMessagesByRoomId(String roomId);

    void saveMessage(ChatMessageDTO message);

    void saveMember(ChatMemberDTO member);

    void deleteMember(ChatMemberDTO member);
}
