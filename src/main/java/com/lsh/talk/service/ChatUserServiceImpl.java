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
                .map(friendMapper::chatFriendToFriendResponseMapper)
                .collect(Collectors.toList());
    }

    /**
     * 친구 추가 서비스,
     * transaction 어노테이션을 활용한 더티체킹
     * 더티체킹: 최초 영속성 컨텍스트(persist context) 등록 후 스냅샷을 뜨며 트랜잭션 종료 시점에 스냅샷과 비교하여 엔티티 변화를 감지하여 자동 업데이트 하는것
     * @param userDetail
     * @param newFriendName
     */
    @Override
    @Transactional
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
