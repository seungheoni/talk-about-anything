package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatProfile;
import com.lsh.talk.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatFriendRepository extends JpaRepository<ChatFriend, UUID> {

    List<ChatFriend> findAllByChatUser(ChatUser chatUser);

    Optional<ChatFriend> findByChatUserId(UUID chatUserId);

    Optional<ChatFriend> findByFriendChatUserId(UUID friendChatUserId);

    List<ChatFriend> findAllByFriendChatUserIdIn(List<UUID> friendChatUserIds);
}