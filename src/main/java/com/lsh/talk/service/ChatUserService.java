package com.lsh.talk.service;

import com.lsh.talk.domain.ChatFriend;
import com.lsh.talk.domain.ChatUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ChatUserService {

    ChatUser getChatUserByChatUser(String userName);

    List<ChatFriend> listOfUsersInFriendRelationship(ChatUser chatUser);

    void addFriend(UserDetails userDetail,String newFriendName);
}
