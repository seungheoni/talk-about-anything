package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    List<ChatRoom> listOfChatRoomsUserBelongsTo(String userName);

    ChatRoom getChatRoomById(UUID roomId);

    void createChatRoom(UserDetails loginUser, List<String> userNames);
}
