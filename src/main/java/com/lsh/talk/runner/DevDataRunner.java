package com.lsh.talk.runner;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Profile("dev")
public class DevDataRunner implements ApplicationRunner {

    private final ChatUserRepository chatUserRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatFriendRepository chatFriendRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;

    public DevDataRunner(ChatUserRepository chatUserRepository, ChatRoomRepository chatRoomRepository,
                         ChatFriendRepository chatFriendRepository, ChatRoomParticipantRepository chatRoomParticipantRepository) {
        this.chatUserRepository = chatUserRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatFriendRepository = chatFriendRepository;
        this.chatRoomParticipantRepository = chatRoomParticipantRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ChatUser seongheon = new ChatUser();
        seongheon.setName("seongheon");
        seongheon.setPassword("password");
        seongheon.setHashType("SHA-256");
        seongheon.setUpdateDate(Instant.now());
        seongheon.setCreateDate(Instant.now());
        chatUserRepository.save(seongheon);

        ChatUser friend1 = new ChatUser();
        friend1.setName("friend1");
        friend1.setPassword("password");
        friend1.setHashType("SHA-256");
        friend1.setUpdateDate(Instant.now());
        friend1.setCreateDate(Instant.now());
        chatUserRepository.save(friend1);

        ChatUser friend2 = new ChatUser();
        friend2.setName("friend2");
        friend2.setPassword("password");
        friend2.setHashType("SHA-256");
        friend2.setUpdateDate(Instant.now());
        friend2.setCreateDate(Instant.now());
        chatUserRepository.save(friend2);

        ChatUser seongheonRes = chatUserRepository.findById(seongheon.getId()).orElse(null);
        ChatUser friend1Res = chatUserRepository.findById(friend1.getId()).orElse(null);

        ChatFriend chatFriend1 = new ChatFriend();
        chatFriend1.setChatUser(seongheonRes);
        chatFriend1.setFriendChatUser(friend1Res);
        chatFriend1.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend1);

        ChatUser seongheonRes2 = chatUserRepository.findById(seongheon.getId()).orElse(null);
        ChatUser friend1Res2 = chatUserRepository.findById(friend1.getId()).orElse(null);

        ChatFriend chatFriend2 = new ChatFriend();
        chatFriend2.setChatUser(seongheonRes2);
        chatFriend2.setFriendChatUser(friend1Res2);
        chatFriend2.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend2);

        ChatRoom room1 = new ChatRoom();
        room1.setName("Room1");
        room1.setCreatedDate(Instant.now());
        room1.setCreatedChatUser(seongheon);
        chatRoomRepository.save(room1);

        ChatRoom room2 = new ChatRoom();
        room2.setName("Room2");
        room2.setCreatedDate(Instant.now());
        room2.setCreatedChatUser(seongheon);
        chatRoomRepository.save(room2);

        ChatRoom chatRoomRes = chatRoomRepository.findById(seongheon.getId()).orElse(null);

        ChatRoomParticipant participant1 = new ChatRoomParticipant();
        participant1.setChatRoom(chatRoomRes);
        participant1.setChatUser(seongheonRes);
        participant1.setJoinedDate(Instant.now());
        chatRoomParticipantRepository.save(participant1);

        ChatRoomParticipant participant2 = new ChatRoomParticipant();
        participant2.setChatRoom(chatRoomRes);
        participant2.setChatUser(seongheonRes2);
        participant2.setJoinedDate(Instant.now());
        chatRoomParticipantRepository.save(participant2);
    }
}