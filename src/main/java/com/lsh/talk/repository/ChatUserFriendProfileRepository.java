package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatUserFriendProfileRepository extends JpaRepository<ChatFriend, UUID> {

    @Query("SELECT p.name FROM ChatFriend cf " +
            "JOIN ChatProfile p ON cf.friendChatUser.id = p.chatUser.id " +
            "WHERE cf.chatUser.id = :userId")
    List<String> findFriendsAndProfiles(@Param("userId") UUID userId);
}
