package com.lsh.talk.controller.rest;

import com.lsh.talk.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping(value="/chatrooms",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createChatRoom(@AuthenticationPrincipal UserDetails loginUser, @RequestBody List<String> userNames) {

        chatRoomService.createChatRoom(loginUser, userNames);
    }
}
