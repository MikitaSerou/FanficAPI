create table if not exists role
(
    id serial not null
    constraint role_pkey
    primary key,
    name varchar(255) not null
    );


create table if not exists tag
(
    id bigserial not null
    constraint tag_pkey
    primary key,
    name varchar(255)
    );


create table if not exists theme
(
    id serial not null
    constraint theme_pkey
    primary key,
    name varchar(255) not null	unique
    );


create table if not exists users
(
    id bigserial not null
    constraint users_pkey
    primary key,
    email varchar(50) not null
    unique,
    password varchar(120) not null,
    username varchar(20) not null
    unique
    );


create table if not exists preferences
(
    user_id bigint not null
    references users,
    theme_id integer not null
    references theme,
    constraint preferences_pkey
    primary key (theme_id, user_id)
    );



create table if not exists publication
(
    id bigserial not null
    constraint publication_pkey
    primary key,
    description varchar(255),
    name varchar(255) not null,
    user_id bigint
    references users,
    theme_id integer
    references theme
    );


create table if not exists bookmarks
(
    user_id bigint not null
    references users,
    publication_id bigint not null
    references publication,
    constraint bookmarks_pkey
    primary key (publication_id, user_id)
    );



create table if not exists chapter
(
    id bigserial not null
    constraint chapter_pkey
    primary key,
    image_reference varchar(255),
    name varchar(255) not null,
    text varchar(255),
    publication_id bigint
    references publication
    );


create table if not exists likes
(
    user_id bigint not null
    references users,
    publication_id bigint not null
    references publication,
    constraint likes_pkey
    primary key (publication_id, user_id)
    );


create table if not exists publication_tag
(
    tag_id bigint not null
    references tag,
    publication_id bigint not null
    references publication,
    constraint publication_tag_pkey
    primary key (publication_id, tag_id)
    );



create table if not exists user_roles
(
    user_id bigint not null
    references users,
    role_id smallint not null
    references role,
    constraint user_roles_pkey
    primary key (user_id, role_id)
    );

INSERT INTO public.role (name)
VALUES ('ROLE_USER');
INSERT INTO public.role (name)
VALUES ('ROLE_ADMIN');

INSERT INTO public.users (email, password, username)
VALUES ('miita.serou@gmail.com', '$2a$10$OWTmnPjs4sDbJIEmId57ferf9incJ3J5OWlS3eBPWjkPvfJB2vBbO', 'Mikita');

INSERT INTO public.users (email, password, username)
VALUES ('Evgeny@gmail.com', '$2a$10$OWTmnPjs4sDbJIEmId57ferf9incJ3J5OWlS3eBPWjkPvfJB2vBbO', 'Evgeny');

INSERT INTO public.user_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id)
VALUES (2, 1);

INSERT INTO TAG (name) VALUES('scary');

INSERT INTO theme (name) VALUES ('IT and Stephen King Universe');

INSERT INTO publication (description, name, user_id, theme_id)
VALUES('Another one story about st.Mann', 'Small talk on the bus station', 1, 1);

INSERT INTO publication_tag (tag_id, publication_id) VALUES (1, 1);

INSERT INTO bookmarks (user_id, publication_id) VALUES (1,1);
INSERT INTO bookmarks (user_id, publication_id) VALUES (2,1);

INSERT INTO likes (user_id, publication_id) VALUES (1,1);

INSERT INTO preferences (user_id, theme_id) VALUES (1,1);

INSERT INTO chapter (image_reference, name, text, publication_id)
VALUES (null, 'Sometimes i liked to take a nap in public places',
        'Once I, passing by the diner that was next to the fair ...',1);