package com.lsh.talk.domain.idclass;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ChatRoomParticipantId implements Serializable {

    private UUID chatRoom;
    private UUID chatUser;
}