CREATE TABLE chat_friend (
                    chat_user_id UUID NOT NULL REFERENCES chat_user(id),
                    friend_chat_user_id UUID NOT NULL REFERENCES chat_user(id),
                    name VARCHAR(255) NOT NULL,
                    created_date TIMESTAMP NOT NULL,
                    PRIMARY KEY (chat_user_id, friend_chat_user_id)
);

COMMENT ON COLUMN chat_friend.chat_user_id IS '대상 유저 id';
COMMENT ON COLUMN chat_friend.friend_chat_user_id IS '친구 관계인 유저 id';
COMMENT ON COLUMN chat_friend.name IS '친구 이름';