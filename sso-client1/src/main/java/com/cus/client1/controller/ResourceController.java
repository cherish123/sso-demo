package com.cus.client1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client1")
public class ResourceController {

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }
}
