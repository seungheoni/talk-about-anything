package com.lsh.talk.controller.rest;

import com.lsh.talk.dto.request.FriendRequest;
import com.lsh.talk.dto.response.FriendProfileResponse;
import com.lsh.talk.service.ChatFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final ChatFriendService chatFriendService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addFriend(@AuthenticationPrincipal UserDetails userDetail,@RequestBody FriendRequest friendRequest) {

        chatFriendService.addFriend(userDetail,friendRequest.getFriendName());
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<FriendProfileResponse> getFriends(@AuthenticationPrincipal UserDetails userDetail) {

        return chatFriendService.listOfUsersInFriendRelationship(userDetail.getUsername());
    }
}
