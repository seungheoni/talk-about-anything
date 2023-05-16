package com.lsh.talk.runner;

import com.lsh.talk.config.coder.SHA256PasswordEncoder;
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

        ChatUser friend1 = new ChatUser();
        friend1.setName("friend1");
        friend1.setPassword(passwordEncoder.encode("qwer1234"));
        friend1.setHashType("SHA-256");
        friend1.setUpdateDate(Instant.now());
        friend1.setCreateDate(Instant.now());
        chatUserRepository.save(friend1);

        ChatUser friend2 = new ChatUser();
        friend2.setName("friend2");
        friend2.setPassword(passwordEncoder.encode("qwer1234"));
        friend2.setHashType("SHA-256");
        friend2.setUpdateDate(Instant.now());
        friend2.setCreateDate(Instant.now());
        chatUserRepository.save(friend2);

        ChatFriend chatFriend1 = new ChatFriend();
        chatFriend1.setChatUser(seongheon);
        chatFriend1.setFriendChatUser(friend1);
        chatFriend1.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend1);

        ChatFriend chatFriend2 = new ChatFriend();
        chatFriend2.setChatUser(seongheon);
        chatFriend2.setFriendChatUser(friend2);
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