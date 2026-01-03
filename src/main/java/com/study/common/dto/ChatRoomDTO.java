package com.study.common.dto;

import lombok.Getter;
import lombok.Setter;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션
    private Boolean secretYn;
    private String password;
    

    public static ChatRoomDTO create(ChatRoomDTO params){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = params.getName();
        room.secretYn = params.getSecretYn();
        room.password = params.getPassword();
//        room.password = passwordEncoder.encode(params.getPassword());
        return room;
    }
    
    public void encodingPassword(PasswordEncoder passwordEncoder) {
    	if (StringUtils.isEmpty(password)) {
    		return;
    	}
    	password = passwordEncoder.encode(password); //다시보기 getpassword(); 차이
    }
    
    public void clearPassword() {
        this.password = "";
    }
    
    
}