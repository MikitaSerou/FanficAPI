create table refresh_token
(
    id              bigserial
        constraint refresh_token_pkey
            primary key,
    expiration_date timestamp    not null,
    token           varchar(255) not null
        unique,
    user_id         bigint
        references users
);