package com.lsh.talk.repository;

import com.lsh.talk.DbIntegrationTest;
import com.lsh.talk.domain.*;
import com.lsh.talk.service.ChatRoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomParticipantRepositoryTest extends DbIntegrationTest {

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Autowired
    private ChatRoomParticipantRepository chatRoomParticipantRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatFriendRepository chatFriendRepository;

    @Test
    @DisplayName("유저정보를 통하여 유저정보에 해당하는 채팅방 목록 조회")
    void findAllByChatUser() {
        ChatUser user = chatUserRepository.findByUniqueName("seongheon").orElseThrow();
        List<ChatRoomParticipant> chatRoomParticipants = chatRoomParticipantRepository.findAllByChatUser(user);

        assertNotNull(chatRoomParticipants);
    }

    @Test
    @DisplayName("채팅방 id에 해당하는 전체목록조회")
    void findAllByChatRoom() {
        ChatUser user = chatUserRepository.findByUniqueName("seongheon").orElseThrow();
        List<ChatRoomParticipant> chatRooms = chatRoomParticipantRepository.findAllByChatUser(user);
        List<ChatRoomParticipant> chatRoomParticipants = chatRoomParticipantRepository.findAllByChatRoom(chatRooms.get(0).getChatRoom());

        assertNotNull(chatRoomParticipants);
    }

    @Test
    @DisplayName("로그인한 사용자 id를 기준으로 방id를 조회한다음, 방id를 기준으로 채팅방참가자와 친구목록을 left join 한다")
    void findChatRoomParticipants() {

        createChatroom();

        ChatUser chatUser = chatUserRepository.findByUniqueName("seongheon").orElseThrow();

        List chatRoomParticipants = chatRoomParticipantRepository.findChatRoomParticipants(chatUser.getId());

        System.out.println(chatRoomParticipants);
    }

    public void createChatroom() {

        ChatUser loginChatUser = chatUserRepository.findByUniqueName("seongheon").orElseThrow();

        // 새로운 채팅 방 생성
        ChatRoom newChatRoom = ChatRoom.builder()
                .createdDate(Instant.now())
                .createdChatUser(loginChatUser)
                .build();


        chatRoomRepository.save(newChatRoom);

        //신규 방에 추가한 친구 정보들을 해당 방 참여자 정보에 포함시킨다.
        List<ChatFriend> friends = chatFriendRepository.findAll();
        List<ChatRoomParticipant> chatRoomParticipants = friends.stream()
                .map(f -> ChatRoomParticipant.builder()
                        .chatUser(f.getFriendChatUser())
                        .chatRoom(newChatRoom)
                        .joinedDate(Instant.now())
                        .build())
                .collect(Collectors.toList());

        //마지막에 로그인한 본인도 생성한 방에 참여시킨다.
        chatRoomParticipants.add(ChatRoomParticipant.builder()
                .chatUser(loginChatUser)
                .chatRoom(newChatRoom)
                .joinedDate(Instant.now())
                .build());

        chatRoomParticipantRepository.saveAll(chatRoomParticipants);

    }
}