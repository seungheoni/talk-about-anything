package com.lsh.talk.domain.idclass;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ChatUserId implements Serializable {

    private UUID id;
    private String name;

}