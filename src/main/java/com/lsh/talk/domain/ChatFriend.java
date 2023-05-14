package com.lsh.talk.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "chat_friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatFriend {

    @Id
    @ManyToOne
    @JoinColumn(name="chat_user_id", nullable=false)
    private ChatUser chatUser;

    @Id
    @ManyToOne
    @JoinColumn(name="friend_chat_user_id", nullable=false)
    private ChatUser friendChatUser;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

}
