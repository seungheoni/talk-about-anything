package com.lsh.talk.service;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.dto.response.FriendResponse;
import com.lsh.talk.entitymap.FriendMapper;
import com.lsh.talk.repository.ChatFriendRepository;
import com.lsh.talk.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    private final ChatFriendRepository chatFriendRepository;

    private final FriendMapper friendMapper;

    @Override
    public List<FriendResponse> listOfUsersInFriendRelationship(String userName) {

        ChatUser chatUser = chatUserRepository.findByName(userName).orElseThrow();

        return chatFriendRepository.findAllByChatUser(chatUser).stream()
                .map(friendMapper::ChatFriendToFriendResponseMapper)
                .collect(Collectors.toList());
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
