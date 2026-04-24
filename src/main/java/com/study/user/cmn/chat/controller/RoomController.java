package com.study.user.cmn.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.common.CommonController;
import com.study.user.cmn.chat.dto.ChatRoomDTO;
import com.study.user.cmn.chat.service.ChatRoomService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user/cmn/chat")
@Log4j2
public class RoomController extends CommonController {

    private final ChatRoomService repository;

    //채팅방 목록 조회
    @GetMapping("")
    public ModelAndView rooms(){
        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("user/cmn/chat/rooms");
        mv.addObject("list", repository.findAllRooms());
        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam("name") String name, final ChatRoomDTO params, RedirectAttributes rttr){
        log.info("# Create Chat Room , name: " + name);
        params.setRegId(getUserId());
        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(params));
        return "redirect:/user/cmn/chat";
    }

    //채팅방 조회(접근)
    @GetMapping("/room")
    public void getRoom(@RequestParam(value="roomId") String roomId, Model model){
        log.info("# get Chat Room, roomID : " + roomId);
        model.addAttribute("room", repository.findRoomById(roomId));
    }
}
