package com.lsh.talk.entitymap;

import com.lsh.talk.domain.ChatProfile;
import com.lsh.talk.domain.ChatUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(source = ".", target = "chatUser")
    @Mapping(source = "uniqueName", target = "name")
    @Mapping(target = "profileIcon", ignore = true)
    ChatProfile chatUserToChatProfile(ChatUser chatUser);
}
