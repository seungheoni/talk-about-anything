package com.lsh.talk.repository;

import com.lsh.talk.DbIntegrationTest;
import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatFriendRepositoryTest extends DbIntegrationTest {

    @Autowired
    private ChatUserRepository chatUserRepository;
    @Autowired
    private ChatFriendRepository chatFriendRepository;

    @Test
    @DisplayName("유저객체 정보 자체를 통하여 해당 유저 이름에 해당하는 친구 목록 조회")
    void findAllByChatUser() {
        ChatUser chatUser = chatUserRepository.findByUniqueName("seongheon").orElseThrow();
        List<ChatFriend> chatFriends  = chatFriendRepository.findAllByChatUser(chatUser);
        assertNotNull(chatFriends);
    }
}