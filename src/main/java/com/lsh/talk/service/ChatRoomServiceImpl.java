package com.lsh.talk.service;

import com.lsh.talk.domain.*;
import com.lsh.talk.dto.ParticipantFriendDTO;
import com.lsh.talk.dto.response.ChatRoomResponse;
import com.lsh.talk.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomParticipantRepository chatRoomParticipantRepository;
    private final ChatUserRepository chatUserRepository;
    private final ChatFriendRepository chatFriendRepository;

    @Override
    public List<ChatRoomResponse> listOfChatRoomsUserBelongsTo(String userName) {

        ChatUser loginUser = chatUserRepository.findByUniqueName(userName).orElseThrow();

        List<List<ParticipantFriendDTO>> participantInChatRooms = chatRoomParticipantRepository.findChatRoomParticipants(loginUser.getId());

        List<ChatRoomResponse> chatRoomResponses = new ArrayList<>();

        participantInChatRooms.forEach(room -> {

            String subjects = room.stream()
                    .map(r -> r.getName())
                    .collect(Collectors.toList()).toString();

            chatRoomResponses.add(ChatRoomResponse.builder()
                    .id(room.get(0).getChatRoomId()).name(subjects).build());
        });

        return chatRoomResponses;
//
//        lists.stream().map(list -> {
//
//            list.stream().forEach(participantFriendDTO -> {
//                participantFriendDTO.
//            });
//        })
//        List<String> chatRoomSubject = chatFriendRepository.findAllByChatUser(participantsUserId)
//                .stream().map(ChatFriend::getName).toList();
//
//        return ChatRoomResponse.builder()
//                .id(chatRoom.getId())
//                .name(chatRoomSubject.toString())
//                .build();;
        /*return chatRoomParticipantRepository.findAllByChatUser(loginUser)
                .stream().map(ChatRoomParticipant::getChatRoom).map(chatRoom -> {

                    List<UUID> participantsUserId = new ArrayList<>();

                    //해당 채팅방 참가자 프로필정보를 조회하여 채팅방 제목을 설정한다.
                    chatRoomParticipantRepository.findAllByChatRoom(chatRoom)
                            .stream()
                            .filter(rp->!rp.getChatUser().getUniqueName().equals(loginUser.getUniqueName()))
                            .forEach(rp -> participantsUserId.add(rp.getChatUser().getId()));


                    List<ChatFriend> loginUserFriends = chatFriendRepository.findAllByFriendChatUserIdIn(participantsUserId);

                    List<String> chatRoomSubject = chatFriendRepository.findAllByChatUser(participantsUserId)
                            .stream().map(ChatFriend::getName).toList();

                    return ChatRoomResponse.builder()
                            .id(chatRoom.getId())
                            .name(chatRoomSubject.toString())
                            .build();

                }).toList();*/
    }

    @Override
    public ChatRoom getChatRoomById(UUID roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    @Override
    public void createChatRoom(UserDetails loginUser, List<UUID> chatUserIds) {

        ChatUser loginChatUser = chatUserRepository.findByUniqueName(loginUser.getUsername()).orElseThrow();

        // 새로운 채팅 방 생성
        ChatRoom newChatRoom = ChatRoom.builder()
                .createdDate(Instant.now())
                .createdChatUser(loginChatUser)
                .build();

        chatRoomRepository.save(newChatRoom);

        //신규 방에 추가한 친구 정보들을 해당 방 참여자 정보에 포함시킨다.
        List<ChatFriend> friends = chatFriendRepository.findAllByFriendChatUserIdIn(chatUserIds);
        List<ChatRoomParticipant> chatRoomParticipants = friends.stream()
                                                                     .map(f -> ChatRoomParticipant.builder()
                                                                                .chatUser(f.getFriendChatUser())
                                                                                .chatRoom(newChatRoom)
                                                                                .joinedDate(Instant.now())
                                                                                .build())
                                                                     .collect(Collectors.toList());

        //마지막에 로그인한 본인도 생성한 방에 참여시킨다.
        chatRoomParticipants.add(ChatRoomParticipant.builder()
                .chatUser(loginChatUser)
                .chatRoom(newChatRoom)
                .joinedDate(Instant.now())
                .build());

        chatRoomParticipantRepository.saveAll(chatRoomParticipants);
    }
}
