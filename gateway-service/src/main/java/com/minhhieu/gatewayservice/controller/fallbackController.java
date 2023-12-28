package com.minhhieu.gatewayservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class fallbackController {
    @GetMapping("message")
    public String messageFallback(){
        return "Call Service Fail";
    }
}
