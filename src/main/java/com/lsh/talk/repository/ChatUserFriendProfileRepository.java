package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.dto.response.FriendProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatUserFriendProfileRepository extends JpaRepository<ChatFriend, UUID> {

    @Query("SELECT new com.lsh.talk.dto.response.FriendProfileResponse(p.chatUser.id, cf.name) FROM ChatFriend cf " +
            "JOIN ChatProfile p ON cf.friendChatUser.id = p.chatUser.id " +
            "WHERE cf.chatUser.id = :userId")
    List<FriendProfileResponse> findFriendsAndProfiles(@Param("userId") UUID userId);
}