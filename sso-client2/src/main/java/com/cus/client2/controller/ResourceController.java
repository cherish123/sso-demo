package com.cus.client2.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/client2")
public class ResourceController {

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/me")
    @ResponseBody
    public String me() {
        return "mememmemmeme";
    }
}
