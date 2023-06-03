package com.lsh.talk.service;

import com.lsh.talk.dto.response.FriendResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ChatUserService {


    List<FriendResponse> listOfUsersInFriendRelationship(String userName);

    void addFriend(UserDetails loginUser,String newFriendName);
}
