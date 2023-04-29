CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE users (
   user_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
   name varchar(255) NOT NULL,
   password varchar(255) NOT NULL,
   hash_type varchar(50) NOT NULL,
   update_date timestamp NOT NULL,
   create_date timestamp NOT NULL
);

INSERT INTO users (name, password, hash_type, update_date, create_date)
VALUES (
           'admin',
           encode(digest('qwer1234', 'sha256'), 'hex'),
           'sha256',
           now(),
           now()
       );