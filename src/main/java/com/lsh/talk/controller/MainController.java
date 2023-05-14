package com.lsh.talk.controller;

import com.lsh.talk.repository.ChatFriendRepository;
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

    private final ChatFriendRepository chatFriendRepository;

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        model.addAttribute("username", userDetails.getUsername());
//        model.addAttribute("friends", );
//        model.addAttribute("chats", Arrays.asList("Chat 1", "Chat 2", "Chat 3"));
        return "main/main";
    }
}
