package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.dto.response.ChatRoomResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    List<ChatRoomResponse> listOfChatRoomsUserBelongsTo(String userName);

    ChatRoom getChatRoomById(UUID roomId);

    void createChatRoom(UserDetails loginUser, List<UUID> chatUserIds);
}
