package com.example.jwt.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WebController {
    @GetMapping("/")
    public String home(Model model){model.addAttribute("msg","Home");return "index";}
    @GetMapping("/hello")
    public String hello(Model model){model.addAttribute("msg","Hello");return "index";}
}
