package com.lsh.talk.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chat_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String hashType;

    @Column(nullable = false)
    private Instant updateDate;

    @Column(nullable = false)
    private Instant createDate;
}
