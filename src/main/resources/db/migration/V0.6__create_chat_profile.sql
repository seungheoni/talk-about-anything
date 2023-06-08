CREATE TABLE chat_profile (
                              id UUID DEFAULT gen_random_uuid(),
                              chat_user_id UUID NOT NULL REFERENCES chat_user(id),
                              name VARCHAR(255) NOT NULL,
                              profile_icon BYTEA,
                              PRIMARY KEY (id, chat_user_id)
);

COMMENT ON COLUMN chat_profile.chat_user_id IS '해당 유저 id';
COMMENT ON COLUMN chat_profile.name IS '프로필 이름';
COMMENT ON COLUMN chat_profile.profile_icon IS '프로필 아이콘';
