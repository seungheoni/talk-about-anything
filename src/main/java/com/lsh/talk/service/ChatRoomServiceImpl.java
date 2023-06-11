package com.lsh.talk.service;

import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Override
    public void createChatRoom(UserDetails loginUser, List<String> userNames) {

        ChatUser loginChatUser = chatUserRepository.findByName(loginUser.getUsername()).orElseThrow();

        // 새로운 채팅 방 생성
        ChatRoom newChatRoom = ChatRoom.builder()
                .name(userNames.toString())
                .createdDate(Instant.now())
                .createdChatUser(loginChatUser)
                .build();
        chatRoomRepository.save(newChatRoom);

        // 채팅방 유저 할당
        userNames.add(loginChatUser.getName());
        List<ChatRoomParticipant> chatRoomParticipants = chatUserRepository.findByNameIn(userNames)
                .stream().map(u -> ChatRoomParticipant.builder()
                        .chatUser(u)
                        .chatRoom(newChatRoom)
                        .joinedDate(Instant.now()).build()).toList();

        chatRoomParticipantRepository.saveAll(chatRoomParticipants);
    }
}
