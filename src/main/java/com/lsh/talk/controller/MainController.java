package com.lsh.talk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("friends", Arrays.asList("Friend 1", "Friend 2", "Friend 3"));
        model.addAttribute("chats", Arrays.asList("Chat 1", "Chat 2", "Chat 3"));
        return "main/main";
    }
}
