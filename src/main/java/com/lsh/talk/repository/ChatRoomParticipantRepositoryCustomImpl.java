package com.lsh.talk.repository;

import com.lsh.talk.domain.idclass.ChatRoomParticipantId;
import com.lsh.talk.domain.idclass.QChatRoomParticipantId;
import com.lsh.talk.dto.ParticipantFriendDTO;
import com.lsh.talk.dto.QParticipantFriendDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

        //채팅방 id들을 기준으로 채팅방목록과 친구목록을 left join
        for (ChatRoomParticipantId chatRoomId : chatRoomIds) {


            List<ParticipantFriendDTO> results = queryFactory
                    .select(new QParticipantFriendDTO(chatRoomParticipant.chatRoom.id, chatRoomParticipant.chatUser.id, chatFriend.friendChatUser.id,chatFriend.name))
                    .from(chatRoomParticipant)
                    .leftJoin(chatFriend)
                    .on(chatRoomParticipant.chatUser.id.eq(chatFriend.friendChatUser.id))
                    .where(chatRoomParticipant.chatRoom.id.eq(chatRoomId.getChatRoom()))
                    .fetch();

            System.out.println(results);
        }

        return null;
    }
}
