package com.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.common.dto.ChatRoomDTO;
import com.study.common.repository.ChatRoomRepository;
import com.study.domain.post.PostRequest;

import jakarta.servlet.http.HttpServletRequest;

//import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", repository.findAllRooms());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam("name") String name, final ChatRoomDTO params, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(params));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회(접근)
    @GetMapping("/room")
    public void getRoom(@RequestParam(value="roomId") String roomId, Model model){
        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", repository.findRoomById(roomId));
    }
    
    // 비밀방 체크
    @PostMapping("/checkpassword")
    @ResponseBody
    public Boolean checkPost(HttpServletRequest request) {
    	
    	// 1. 회원 정보 조회
    	String roomId = request.getParameter("roomId");
    	String password = request.getParameter("password");
    	Boolean result = repository.secret(roomId, password);
    	
    	return result;
    	
    }
    
    
}
