package com.lsh.talk.repository;

import com.lsh.talk.DbIntegrationTest;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.entitymap.ProfileMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatUserFriendProfileRepositoryTest extends DbIntegrationTest {

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Autowired
    private ChatUserFriendProfileRepository chatUserFriendProfileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Test
    @DisplayName("유저ID를 기반으로 친구목록의 프로필 이름 리스트를 가져오기 테스트")
    void findFriendsAndProfiles() {

        String userName = "seongheon";

        ChatUser chatUser = chatUserRepository.findByName(userName).orElseThrow();

        List<String> profilenames = chatUserFriendProfileRepository.findFriendsAndProfiles(chatUser.getId());

        assertNotNull(profilenames);
    }
}