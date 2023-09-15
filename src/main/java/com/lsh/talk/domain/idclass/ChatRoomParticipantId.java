package com.lsh.talk.domain.idclass;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class ChatRoomParticipantId implements Serializable {

    private UUID chatRoom;
    private UUID chatUser;
    @QueryProjection
    public ChatRoomParticipantId(UUID chatRoom, UUID chatUser) {
        this.chatRoom = chatRoom;
        this.chatUser = chatUser;
    }

    @QueryProjection
    public ChatRoomParticipantId(UUID chatRoom) {
        this.chatRoom = chatRoom;
    }
}