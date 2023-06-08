package com.lsh.talk.domain;

import com.lsh.talk.domain.idclass.ChatProfileId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "chat_profile")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ChatProfileId.class)
public class ChatProfile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Id
    @ManyToOne
    @JoinColumn(name="chat_user_id", nullable=false)
    private ChatUser chatUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_icon")
    private Byte[] profileIcon;
}