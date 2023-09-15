CREATE TABLE chat_user (
                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                    uniqueName VARCHAR(255) UNIQUE NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    hash_type VARCHAR(20) NOT NULL,
                    update_date TIMESTAMP NOT NULL,
                    create_date TIMESTAMP NOT NULL
);

COMMENT ON COLUMN chat_user.id IS 'id';
COMMENT ON COLUMN chat_user.uniqueName IS '계정 이름';
COMMENT ON COLUMN chat_user.password IS '계정 패스워드';
COMMENT ON COLUMN chat_user.hash_type IS '계정 패스워드 암호화 알고리즘';
COMMENT ON COLUMN chat_user.update_date IS '계정 업데이트 날짜';
COMMENT ON COLUMN chat_user.create_date IS '계정 생성 날짜';