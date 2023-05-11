package com.lsh.talk.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "chat_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String hashType;

    @Column(nullable = false)
    private Instant updateDate;

    @Column(nullable = false)
    private Instant createDate;
}
