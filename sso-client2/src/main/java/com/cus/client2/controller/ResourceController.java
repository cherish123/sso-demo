package com.cus.client2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/client2")
public class ResourceController {

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }
}
