package com.lsh.talk.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room_participant")
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
    private LocalDateTime joinedDate;
}