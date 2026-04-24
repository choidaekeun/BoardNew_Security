package com.study.user.cmn.chat.dto;

import lombok.Getter;
import lombok.Setter;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomId;
    private String name;
    private Boolean secretYn;
    private String password;
    private String regId;
    private String regDt;
    private List<ChatMemberDTO> chatMembers;


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
    	password = passwordEncoder.encode(password);
    }

    public void clearPassword() {
        this.password = "";
    }
}
