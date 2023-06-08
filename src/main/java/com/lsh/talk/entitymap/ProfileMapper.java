package com.lsh.talk.entitymap;

import com.lsh.talk.domain.UserFriendProfile;
import com.lsh.talk.dto.response.FriendProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProfileMapper {

    @Mapping(source = "profile.name",target = "friendName")
    FriendProfileResponse userFriendProfileToFriendResponseMapper(UserFriendProfile userFriendProfile);

    @Mapping(source = ".",target = "friendName")
    FriendProfileResponse stringToFriendResponseMapper(String profileName);
}
