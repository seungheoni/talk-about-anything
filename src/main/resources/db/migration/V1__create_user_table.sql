CREATE TABLE users (
   user_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
   name varchar(255) NOT NULL,
   password varchar(255) NOT NULL,
   hash_type varchar(50) NOT NULL,
   update_date date NOT NULL,
   create_date date NOT NULL
);