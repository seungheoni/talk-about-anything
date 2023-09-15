package com.lsh.talk.repository;

import com.lsh.talk.domain.ChatProfile;
import com.lsh.talk.domain.QChatProfile;
import com.lsh.talk.domain.idclass.ChatRoomParticipantId;
import com.lsh.talk.domain.idclass.QChatRoomParticipantId;
import com.lsh.talk.dto.ParticipantFriendDTO;
import com.lsh.talk.dto.QParticipantFriendDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.lsh.talk.domain.QChatProfile.chatProfile;
import static com.lsh.talk.domain.QChatRoomParticipant.chatRoomParticipant;

import static com.lsh.talk.domain.QChatFriend.chatFriend;

@Repository
@RequiredArgsConstructor
public class ChatRoomParticipantRepositoryCustomImpl implements ChatRoomParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<List<ParticipantFriendDTO>> findChatRoomParticipants(UUID userId) {

        List<ChatRoomParticipantId> chatRoomIds = queryFactory.select(new QChatRoomParticipantId(chatRoomParticipant.chatRoom.id))
                                                                        .from(chatRoomParticipant)
                                                                        .where(chatRoomParticipant.chatUser.id.eq(userId)).fetch();

        List<List<ParticipantFriendDTO>> participantInChatRooms = new ArrayList<>();

        //채팅방 id들을 기준으로 채팅방목록과 친구목록을 left join
        for (ChatRoomParticipantId chatRoomId : chatRoomIds) {
            
            // 친구인 목록 뽑아내기 아닌 유저는 null 정보로 들어감
            List<ParticipantFriendDTO> participants = queryFactory
                    .select(new QParticipantFriendDTO(chatRoomParticipant.chatRoom.id, chatRoomParticipant.chatUser.id, chatFriend.friendChatUser.id,chatFriend.name))
                    .from(chatRoomParticipant)
                    .leftJoin(chatFriend)
                    .on(chatRoomParticipant.chatUser.id.eq(chatFriend.friendChatUser.id))
                    .where(chatRoomParticipant.chatRoom.id.eq(chatRoomId.getChatRoom()))
                    .fetch();

            //친구가 아닌 null 목록들은 다시 모아서 조회해서 프로필 정보(이름) 찾기
            List<UUID> notFriendUserIds = participants.stream().filter(p -> p.getName() == null)
                    .map(ParticipantFriendDTO::getChatUserId).toList();

            List<ChatProfile> notFriendUsers = queryFactory
                    .select(new QChatProfile(chatProfile))
                    .from(chatProfile)
                    .where(chatProfile.chatUser.id.in(notFriendUserIds))
                    .fetch();

            //친구가 아닌 참가자 프로필 정보를 토대로 null 값 이름 세팅
            participants.stream().filter(participantFriendDTO -> participantFriendDTO.getName() == null).forEach(p-> {

                for(ChatProfile c : notFriendUsers) {
                    if(p.getChatUserId().equals(c.getChatUser().getId())) {
                        p.setName(c.getName());
                    }
                }
            });

            participantInChatRooms.add(participants);
        }

        return participantInChatRooms;
    }
}
