package com.lsh.talk.service;

import com.lsh.talk.dto.request.FriendNameChangeRequest;
import com.lsh.talk.dto.response.FriendProfileResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ChatFriendService {


    List<FriendProfileResponse> listOfUsersInFriendRelationship(String userName);

    void addFriend(UserDetails loginUser,String newFriendName);

    void editFriendName(FriendNameChangeRequest friendNameChangeRequest);
}
