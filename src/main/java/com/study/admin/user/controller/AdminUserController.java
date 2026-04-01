package com.study.admin.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @GetMapping("/usermng")
    public String openUserList() {
        return "/admin/user/usermng";
    }

}