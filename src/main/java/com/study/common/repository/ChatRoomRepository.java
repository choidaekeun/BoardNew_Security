package com.study.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.study.common.dto.ChatMessageDTO;
import com.study.common.dto.ChatRoomDTO;
import com.study.domain.post.PostResponse;

import jakarta.annotation.PostConstruct;
import java.util.*;
//import java.util.stream.Stream;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDTO> chatRoomDTOMap;
    private Map<String, List<ChatMessageDTO>> chatMessageMap;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
        chatMessageMap = new HashMap<>(); // 메시지들을 저장할 맵 초기화
    }

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }
    
    // 특정 채팅방의 메시지 리스트를 반환
    public List<ChatMessageDTO> chatInfoList(String roomId) {
        
        return chatMessageMap.getOrDefault(roomId, Collections.emptyList());
    }

    public ChatRoomDTO findRoomById(String id){ //다시보기 데이터읽어 오는 부분
        return chatRoomDTOMap.get(id); // Map<String, ChatRoomDTO> key:String  value:ChatRoomDTO
    }

    public ChatRoomDTO createChatRoomDTO(ChatRoomDTO params){
        
//    	ChatRoomDTO room = ChatRoomDTO.create(params, passwordEncoder);    	
    	ChatRoomDTO room = ChatRoomDTO.create(params);
        
    	// 비밀번호 암호화
    	if (params.getSecretYn()) {
    		room.encodingPassword(passwordEncoder);
    	}
    	
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
    
    /**
     * 비밀방
     * @param roomId - 비밀방 ID
     * @param password - 비밀번호
     * @return Boolean
     */
    public Boolean secret(final String roomId, final String password) {
 	   
 	   // 1. 회원 정보 및 비밀번호 조회
    	ChatRoomDTO room = findRoomById(roomId);
        String encodedPassword = (room == null) ? "" : room.getPassword();
        
        // 2. 회원 정보 및 비밀번호 체크
        Boolean	result = passwordEncoder.matches(password, encodedPassword);	
        if (room == null) {
            return null;
        }
        if (result == false) {
        	return false;
        }

        return result;
    }
    
    // 입력된 메시지 리스트에 추가
    public void addMessageToRoom(String roomId, ChatMessageDTO message) {
        // roomId에 해당하는 메시지 리스트를 가져옵니다
        List<ChatMessageDTO> messages = chatMessageMap.get(roomId);
        
        // 리스트가 없으면 새 리스트를 생성하여 추가
        if (messages == null) {
            messages = new ArrayList<>();
            chatMessageMap.put(roomId, messages);
        }
        
        // 메시지를 리스트에 추가
        messages.add(message);
    }
    
    
    
}
