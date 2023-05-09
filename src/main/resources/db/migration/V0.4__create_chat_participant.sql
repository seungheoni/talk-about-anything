CREATE TABLE chat_room_participant (
                    chat_room_id UUID NOT NULL REFERENCES chat_room(id),
                    chat_user_id UUID NOT NULL REFERENCES chat_user(id),
                    joined_date TIMESTAMP NOT NULL,
                    PRIMARY KEY (chat_room_id, chat_user_id)
);

COMMENT ON COLUMN chat_room_participant.chat_room_id IS '채팅방 id';
COMMENT ON COLUMN chat_room_participant.chat_user_id IS '채팅방에 속한 유저 id';
COMMENT ON COLUMN chat_room_participant.joined_date IS '채팅방에 유저가 참여한 날짜';