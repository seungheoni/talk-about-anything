INSERT INTO chat_user (uniqueName, password, hash_type, update_date, create_date)
VALUES (
           'admin',
           encode(digest('qwer1234', 'sha256'), 'hex'),
           'sha256',
           now(),
           now()
       );