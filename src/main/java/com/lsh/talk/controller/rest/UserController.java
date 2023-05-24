package com.lsh.talk.controller.rest;

import com.lsh.talk.dto.request.FriendRequest;
import com.lsh.talk.service.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final ChatUserService chatUserService;

    @PostMapping(value="/add-friend",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addFriend(@AuthenticationPrincipal UserDetails userDetail,@RequestBody FriendRequest friendRequest) {

        chatUserService.addFriend(userDetail,friendRequest.getFriendName());
    }
}
