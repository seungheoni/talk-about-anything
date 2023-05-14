package com.lsh.talk.domain;

import com.lsh.talk.domain.idclass.ChatRoomParticipantId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room_participant")
@Getter
@Setter
@IdClass(ChatRoomParticipantId.class)
public class ChatRoomParticipant {

    @Id
    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "chat_user_id", nullable = false)
    private ChatUser chatUser;

    @Column(name = "joined_date", nullable = false)
    private Instant joinedDate;
}