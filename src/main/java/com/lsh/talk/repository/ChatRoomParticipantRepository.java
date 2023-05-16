package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRoomParticipantRepository extends JpaRepository<ChatRoomParticipant, UUID> {

    List<ChatRoomParticipant> findAllByChatUser(ChatUser chatUser);
}