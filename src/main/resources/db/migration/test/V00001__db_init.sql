create table if not exists role
(
    id   serial       not null
        constraint role_pkey
            primary key,
    name varchar(255) not null
);


create table if not exists tag
(
    id   bigserial not null
        constraint tag_pkey
            primary key,
    name varchar(255)
);


create table if not exists theme
(
    id        serial       not null
        constraint theme_pkey
            primary key,
    name      varchar(255) not null unique,
    image_url varchar(255)
);


create table if not exists users
(
    id                bigserial    not null
        constraint users_pkey
            primary key,
    email             varchar(50)  not null
        unique,
    password          varchar(120) not null,
    username          varchar(20)  not null
        unique,
    birth_date        date         NOT NULL DEFAULT '1970-01-01',
    registration_date date                  default current_date
);


create table if not exists users_theme
(
    user_id  bigint  not null
        references users,
    theme_id integer not null
        references theme,
    constraint users_theme_pkey
        primary key (theme_id, user_id)
);



create table if not exists publication
(
    id          bigserial    not null
        constraint publication_pkey
            primary key,
    description varchar(255),
    name        varchar(255) not null,
    user_id     bigint
        references users,
    theme_id    integer
        references theme
);

ALTER TABLE publication
    ADD
        creation_date date default current_date NOT NULL;

create table if not exists bookmarks
(
    user_id        bigint not null
        references users,
    publication_id bigint not null
        references publication,
    constraint bookmarks_pkey
        primary key (publication_id, user_id)
);



create table if not exists chapter
(
    id              bigserial    not null
        constraint chapter_pkey
            primary key,
    image_reference varchar(255),
    name            varchar(255) not null,
    text            varchar(255),
    publication_id  bigint
        references publication
);


create table if not exists likes
(
    user_id        bigint not null
        references users,
    publication_id bigint not null
        references publication,
    constraint likes_pkey
        primary key (publication_id, user_id)
);


create table if not exists publication_tag
(
    tag_id         bigint not null
        references tag,
    publication_id bigint not null
        references publication,
    constraint publication_tag_pkey
        primary key (publication_id, tag_id)
);



create table if not exists user_roles
(
    user_id bigint   not null
        references users,
    role_id smallint not null
        references role,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

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

INSERT INTO role (name)
VALUES ('ROLE_USER');
INSERT INTO role (name)
VALUES ('ROLE_ADMIN');

