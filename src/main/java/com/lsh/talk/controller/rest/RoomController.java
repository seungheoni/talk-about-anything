package com.lsh.talk.controller.rest;

import com.lsh.talk.dto.response.ChatRoomResponse;
import com.lsh.talk.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatrooms")
public class RoomController {

    private final ChatRoomService chatRoomService;


    /**
     * 새로운 채팅방 생성하기
     * @param chatUserIds 초대할 사용자 id
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createChatRoom(@AuthenticationPrincipal UserDetails loginUser, @RequestBody List<UUID> chatUserIds) {

        chatRoomService.createChatRoom(loginUser, chatUserIds);
    }

    /**
     * 채팅방 목록 불러오기
     * @return List<ChatRoomResponse> 채팅방 목록
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChatRoomResponse> getChatRoomList(@AuthenticationPrincipal UserDetails loginUser) {
        String userName = loginUser.getUsername();
        return chatRoomService.listOfChatRoomsUserBelongsTo(userName);
    }
}
