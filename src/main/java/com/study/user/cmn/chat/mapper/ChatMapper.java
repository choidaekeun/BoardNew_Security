package com.study.user.cmn.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.user.cmn.chat.dto.ChatMemberDTO;
import com.study.user.cmn.chat.dto.ChatMessageDTO;
import com.study.user.cmn.chat.dto.ChatRoomDTO;

@Mapper
public interface ChatMapper {

    List<ChatRoomDTO> findAllRooms();

    ChatRoomDTO findRoomById(String roomId);

    void saveRoom(ChatRoomDTO room);

    List<ChatMessageDTO> findMessagesByRoomId(String roomId);

    void saveMessage(ChatMessageDTO message);

    ChatMemberDTO findMember(@Param("roomId") String roomId, @Param("regId") String regId);

    void saveMember(ChatMemberDTO member);

    void deleteMember(ChatMemberDTO member);
}
