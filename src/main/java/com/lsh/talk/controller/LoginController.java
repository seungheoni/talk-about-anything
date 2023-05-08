package com.lsh.talk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password");
        }
        return "login/login";
    }

    @GetMapping("/main")
    public String home(Model model) {
        model.addAttribute("username", "John Doe");
        model.addAttribute("friends", Arrays.asList("Friend 1", "Friend 2", "Friend 3"));
        model.addAttribute("chats", Arrays.asList("Chat 1", "Chat 2", "Chat 3"));
        return "main/main";
    }
}
