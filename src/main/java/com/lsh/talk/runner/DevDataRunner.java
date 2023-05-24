package com.lsh.talk.runner;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevDataRunner implements ApplicationRunner {

    private final ChatUserRepository chatUserRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatFriendRepository chatFriendRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ChatUser seongheon = new ChatUser();
        seongheon.setName("seongheon");
        seongheon.setPassword(passwordEncoder.encode("qwer1234"));
        seongheon.setHashType("SHA-256");
        seongheon.setUpdateDate(Instant.now());
        seongheon.setCreateDate(Instant.now());
        chatUserRepository.save(seongheon);

        ChatUser user1 = new ChatUser();
        user1.setName("simin");
        user1.setPassword(passwordEncoder.encode("qwer1234"));
        user1.setHashType("SHA-256");
        user1.setUpdateDate(Instant.now());
        user1.setCreateDate(Instant.now());
        chatUserRepository.save(user1);

        ChatUser user2 = new ChatUser();
        user2.setName("sunsin");
        user2.setPassword(passwordEncoder.encode("qwer1234"));
        user2.setHashType("SHA-256");
        user2.setUpdateDate(Instant.now());
        user2.setCreateDate(Instant.now());
        chatUserRepository.save(user2);

        ChatUser user3 = new ChatUser();
        user3.setName("gunhee");
        user3.setPassword(passwordEncoder.encode("qwer1234"));
        user3.setHashType("SHA-256");
        user3.setUpdateDate(Instant.now());
        user3.setCreateDate(Instant.now());
        chatUserRepository.save(user3);

        ChatUser user4 = new ChatUser();
        user4.setName("gimi");
        user4.setPassword(passwordEncoder.encode("qwer1234"));
        user4.setHashType("SHA-256");
        user4.setUpdateDate(Instant.now());
        user4.setCreateDate(Instant.now());
        chatUserRepository.save(user4);

        ChatFriend chatFriend1 = new ChatFriend();
        chatFriend1.setChatUser(seongheon);
        chatFriend1.setFriendChatUser(user1);
        chatFriend1.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend1);

        ChatFriend chatFriend2 = new ChatFriend();
        chatFriend2.setChatUser(seongheon);
        chatFriend2.setFriendChatUser(user2);
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

        ChatRoomParticipant participant1 = new ChatRoomParticipant();
        participant1.setChatRoom(room1);
        participant1.setChatUser(seongheon);
        participant1.setJoinedDate(Instant.now());
        chatRoomParticipantRepository.save(participant1);

        ChatRoomParticipant participant2 = new ChatRoomParticipant();
        participant2.setChatRoom(room2);
        participant2.setChatUser(seongheon);
        participant2.setJoinedDate(Instant.now());
        chatRoomParticipantRepository.save(participant2);
    }
}