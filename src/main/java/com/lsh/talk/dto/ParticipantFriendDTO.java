package com.lsh.talk.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ParticipantFriendDTO {

    private UUID chatRoomId;
    private UUID chatUserId;
    private UUID chatFriendId;
    private String name;

    @QueryProjection
    public ParticipantFriendDTO(UUID chatRoomId, UUID chatUserId, UUID chatFriendId, String name) {
        this.chatRoomId = chatRoomId;
        this.chatUserId = chatUserId;
        this.chatFriendId = chatFriendId;
        this.name = name;
    }
}
