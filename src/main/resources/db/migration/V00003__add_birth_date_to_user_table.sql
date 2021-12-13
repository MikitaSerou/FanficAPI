ALTER TABLE users
    ADD COLUMN birth_date date NOT NULL DEFAULT '1970-01-01',
    ADD COLUMN registration_date timestamp default current_timestamp;