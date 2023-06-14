package com.lsh.talk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userName = userDetails.getUsername();
        model.addAttribute("username", userName);
        return "main/main";
    }
}
