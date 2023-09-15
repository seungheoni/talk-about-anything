package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatRoomParticipant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRoomParticipantRepositoryCustom {

    List findChatRoomParticipants(UUID userId);
}