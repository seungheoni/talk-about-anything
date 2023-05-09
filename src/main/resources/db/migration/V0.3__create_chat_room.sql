CREATE TABLE chat_room (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    name VARCHAR(255) NOT NULL,
                    created_date TIMESTAMP NOT NULL,
                    created_chat_user_id UUID NOT NULL REFERENCES chat_user(id)
);

COMMENT ON COLUMN chat_room.id IS 'id';
COMMENT ON COLUMN chat_room.name IS '채팅방 이름';
COMMENT ON COLUMN chat_room.created_date IS '채팅방 생성일';
COMMENT ON COLUMN chat_room.created_chat_user_id IS '채팅방 생성한 유저 id';