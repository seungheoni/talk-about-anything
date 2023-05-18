package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatUser;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    List<ChatRoom> listOfChatRoomsUserBelongsTo(ChatUser chatUser);

    ChatRoom getChatRoomById(UUID roomId);

}
