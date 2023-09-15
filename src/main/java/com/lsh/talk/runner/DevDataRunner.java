package com.lsh.talk.runner;

import com.lsh.talk.domain.*;
import com.lsh.talk.entitymap.UserMapper;
import com.lsh.talk.repository.*;
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
    private final ChatProfileRepository chatProfileRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatFriendRepository chatFriendRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ChatUser seongheon = new ChatUser();
        seongheon.setUniqueName("seongheon");
        seongheon.setPassword(passwordEncoder.encode("qwer1234"));
        seongheon.setHashType("SHA-256");
        seongheon.setUpdateDate(Instant.now());
        seongheon.setCreateDate(Instant.now());
        chatUserRepository.save(seongheon);

        chatProfileRepository.save(userMapper.chatUserToChatProfile(seongheon));

        ChatUser user1 = new ChatUser();
        user1.setUniqueName("simin");
        user1.setPassword(passwordEncoder.encode("qwer1234"));
        user1.setHashType("SHA-256");
        user1.setUpdateDate(Instant.now());
        user1.setCreateDate(Instant.now());
        chatUserRepository.save(user1);

        chatProfileRepository.save(userMapper.chatUserToChatProfile(user1));

        ChatUser user2 = new ChatUser();
        user2.setUniqueName("sunsin");
        user2.setPassword(passwordEncoder.encode("qwer1234"));
        user2.setHashType("SHA-256");
        user2.setUpdateDate(Instant.now());
        user2.setCreateDate(Instant.now());
        chatUserRepository.save(user2);

        chatProfileRepository.save(userMapper.chatUserToChatProfile(user2));

        ChatUser user3 = new ChatUser();
        user3.setUniqueName("gunhee");
        user3.setPassword(passwordEncoder.encode("qwer1234"));
        user3.setHashType("SHA-256");
        user3.setUpdateDate(Instant.now());
        user3.setCreateDate(Instant.now());
        chatUserRepository.save(user3);

        chatProfileRepository.save(userMapper.chatUserToChatProfile(user3));

        ChatUser user4 = new ChatUser();
        user4.setUniqueName("gimi");
        user4.setPassword(passwordEncoder.encode("qwer1234"));
        user4.setHashType("SHA-256");
        user4.setUpdateDate(Instant.now());
        user4.setCreateDate(Instant.now());
        chatUserRepository.save(user4);

        chatProfileRepository.save(userMapper.chatUserToChatProfile(user4));

        ChatFriend chatFriend1 = new ChatFriend();
        chatFriend1.setChatUser(seongheon);
        chatFriend1.setFriendChatUser(user1);
        chatFriend1.setName(user1.getUniqueName());
        chatFriend1.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend1);

        ChatFriend chatFriend2 = new ChatFriend();
        chatFriend2.setChatUser(seongheon);
        chatFriend2.setFriendChatUser(user2);
        chatFriend2.setName(user2.getUniqueName());
        chatFriend2.setCreatedDate(Instant.now());
        chatFriendRepository.save(chatFriend2);

        ChatRoom room1 = new ChatRoom();
        room1.setCreatedDate(Instant.now());
        room1.setCreatedChatUser(seongheon);
        chatRoomRepository.save(room1);

        ChatRoom room2 = new ChatRoom();
        room2.setCreatedDate(Instant.now());
        room2.setCreatedChatUser(seongheon);
        chatRoomRepository.save(room2);
    }
}