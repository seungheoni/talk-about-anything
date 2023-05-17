package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;

import java.util.UUID;

public interface ChatRoomService {
    ChatRoom getChatRoomById(UUID roomId);

}
