package com.raroki.atsumarii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<html><body><h1>Atsumarii API</h1><p>Welcome! The API is running successfully.</p><p>Use /api endpoints to access the REST API.</p></body></html>";
    }
    
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }
}