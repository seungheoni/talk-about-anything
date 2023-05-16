package com.lsh.talk.domain;

import com.lsh.talk.domain.idclass.ChatFriendId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "chat_friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChatFriendId.class)
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
    private Instant createdDate;

}
