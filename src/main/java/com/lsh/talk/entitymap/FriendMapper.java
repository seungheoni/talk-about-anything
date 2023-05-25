package com.lsh.talk.entitymap;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.dto.response.FriendResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface FriendMapper {

    @Mapping(source = "friendChatUser.name",target = "friendName")
    FriendResponse ChatFriendToFriendResponseMapper(ChatFriend chatFriend);
}