package com.cus.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String email() {
        return "这是主页";
    }

    @GetMapping("/admin")
    public String admin() {
        return "这是admin页";
    }

    @GetMapping("/user")
    public String user() {
        return "这是user页";
    }
}
