package com.lsh.talk.service;

import com.lsh.talk.domain.ChatProfile;
import com.lsh.talk.domain.ChatRoom;
import com.lsh.talk.domain.ChatRoomParticipant;
import com.lsh.talk.domain.ChatUser;
import com.lsh.talk.dto.response.ChatRoomResponse;
import com.lsh.talk.repository.ChatProfileRepository;
import com.lsh.talk.repository.ChatRoomParticipantRepository;
import com.lsh.talk.repository.ChatRoomRepository;
import com.lsh.talk.repository.ChatUserRepository;
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

    private final ChatProfileRepository chatProfileRepository;

    @Override
    public List<ChatRoomResponse> listOfChatRoomsUserBelongsTo(String userName) {

        ChatUser loginUser = chatUserRepository.findByName(userName).orElseThrow();

        return chatRoomParticipantRepository.findAllByChatUser(loginUser)
                .stream().map(ChatRoomParticipant::getChatRoom).map(chatRoom -> {

                    List<UUID> participantsUserId = new ArrayList<>();

                    //해당 채팅방 참가자 프로필정보를 조회하여 채팅방 제목을 설정한다.
                    chatRoomParticipantRepository.findAllByChatRoom(chatRoom).stream().filter(rp->!rp.getChatUser().getName().equals(loginUser.getName())).forEach(rp -> participantsUserId.add(rp.getChatUser().getId()));
                    List<String> chatRoomSubject = chatProfileRepository.findAllByChatUserIdIn(participantsUserId).stream().map(ChatProfile::getName).toList();

                    return ChatRoomResponse.builder()
                            .id(chatRoom.getId())
                            .name(chatRoomSubject.toString())
                            .build();

                }).toList();
    }

    @Override
    public ChatRoom getChatRoomById(UUID roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    @Override
    public void createChatRoom(UserDetails loginUser, List<UUID> chatUserIds) {

        ChatProfile loginUserProfile= chatUserRepository.findByName(loginUser.getUsername())
                                                    .map(u-> chatProfileRepository.findByChatUserId(u.getId()).orElseThrow()).orElseThrow();

        List<ChatProfile> participantsProfiles = chatProfileRepository.findAllByChatUserIdIn(chatUserIds);

        participantsProfiles.add(loginUserProfile);

        // 새로운 채팅 방 생성
        ChatRoom newChatRoom = ChatRoom.builder()
                .createdDate(Instant.now())
                .createdChatUser(loginUserProfile.getChatUser())
                .build();

        chatRoomRepository.save(newChatRoom);

        List<ChatRoomParticipant> chatRoomParticipants = participantsProfiles.stream()
                                                                     .map(p -> ChatRoomParticipant.builder()
                                                                                .chatUser(p.getChatUser())
                                                                                .chatRoom(newChatRoom)
                                                                                .joinedDate(Instant.now())
                                                                                .build())
                                                                     .toList();

        chatRoomParticipantRepository.saveAll(chatRoomParticipants);
    }
}
