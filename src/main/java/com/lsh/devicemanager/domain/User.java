package com.lsh.devicemanager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id",updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "hash_type", nullable = false)
    private String hashType;

    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @Column(name = "create_date", nullable = false)
    private Instant createDate;
}
