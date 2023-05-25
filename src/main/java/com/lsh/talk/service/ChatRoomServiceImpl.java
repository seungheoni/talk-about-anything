package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;

    private final ChatUserRepository chatUserRepository;

    @Override
    public List<ChatRoom> listOfChatRoomsUserBelongsTo(String userName) {

        ChatUser chatUser = chatUserRepository.findByName(userName).orElseThrow();
        return chatRoomParticipantRepository.findAllByChatUser(chatUser)
                .stream().map(ChatRoomParticipant::getChatRoom).collect(Collectors.toList());
    }

    @Override
    public ChatRoom getChatRoomById(UUID roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }
}
