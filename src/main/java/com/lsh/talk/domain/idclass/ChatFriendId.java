package com.lsh.talk.domain.idclass;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ChatFriendId implements Serializable {

    private UUID chatUser;
    private UUID friendChatUser;

}