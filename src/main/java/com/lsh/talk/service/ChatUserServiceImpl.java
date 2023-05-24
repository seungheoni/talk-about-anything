package com.lsh.talk.service;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    private final ChatFriendRepository chatFriendRepository;

    @Override
    public ChatUser getChatUserByChatUser(String userName) {

        return chatUserRepository.findByName(userName).orElseThrow();
    }

    @Override
    public List<ChatFriend> listOfUsersInFriendRelationship(ChatUser chatUser) {
        return chatFriendRepository.findAllByChatUser(chatUser);
    }

    @Override
    public void addFriend(UserDetails userDetail, String newFriendName) {
        ChatUser loginUser = chatUserRepository.findByName(userDetail.getUsername()).orElseThrow();
        ChatUser newFriendUser = chatUserRepository.findByName(newFriendName).orElseThrow();

        ChatFriend chatFriend = ChatFriend.builder()
                .chatUser(loginUser)
                .friendChatUser(newFriendUser)
                .createdDate(Instant.now())
                .build();

        chatFriendRepository.save(chatFriend);
    }
}
