package com.lsh.talk.controller;

import com.lsh.talk.service.ChatRoomService;
import com.lsh.talk.service.ChatFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ChatFriendService chatFriendService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userName = userDetails.getUsername();
        model.addAttribute("username", userName);
        model.addAttribute("friends", chatFriendService.listOfUsersInFriendRelationship(userName));
        model.addAttribute("rooms", chatRoomService.listOfChatRoomsUserBelongsTo(userName));
        return "main/main";
    }
}
