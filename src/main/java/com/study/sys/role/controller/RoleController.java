package com.study.sys.role.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @GetMapping("/rolemng")
    public String rolemng() {
        return "sys/role/rolemng";
    }
}
