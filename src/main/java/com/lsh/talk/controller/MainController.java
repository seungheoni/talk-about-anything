package com.lsh.talk.controller;

import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
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

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomParticipantRepository chatRoomParticipantRepository;

    private final ChatUserRepository chatUserRepository;

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        ChatUser user = chatUserRepository.findByName(userDetails.getUsername()).orElseThrow();

        chatRoomParticipantRepository.findAllByChatUser(user);

        model.addAttribute("username", user.getName());
        model.addAttribute("friends", chatFriendRepository.findAllByChatUser(user));
        model.addAttribute("rooms", chatRoomParticipantRepository.findAllByChatUser(user));
        return "main/main";
    }
}
