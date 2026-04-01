package com.study.user.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.common.CommonController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/user")
public class UserController extends CommonController {

    @GetMapping("/updateUser")
    public String updateUser(Model model) {
        model.addAttribute("loginUserId", getUserId());
        return "user/user/update";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        model.addAttribute("loginUserId", getUserId());
        return "user/user/delete";
    }

}
