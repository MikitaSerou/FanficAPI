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

create table if not exists preferences
(
    user_id  bigint  not null
        references users,
    theme_id integer not null
        references theme,
    constraint preferences_pkey
        primary key (user_id, theme_id)
);

create table if not exists publication
(
    id            bigserial                 not null
        constraint publication_pkey
            primary key,
    description   varchar(255),
    name          varchar(255)              not null,
    image_url     varchar(255),
    user_id       bigint
        references users,
    theme_id      integer
        references theme,
    creation_date date default current_date NOT NULL,
    update_date   date default current_date NOT NULL
);

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
    id             bigserial    not null
        constraint chapter_pkey
            primary key,
    image_url      varchar(255),
    name           varchar(255) not null,
    text           varchar(255),
    publication_id bigint
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
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (email, password, username, birth_date, registration_date)
VALUES ('miita.serou@gmail.com', '$2a$10$OWTmnPjs4sDbJIEmId57ferf9incJ3J5OWlS3eBPWjkPvfJB2vBbO', 'Mikita', '1970-01-01',
        now()),
       ('Evgeny@gmail.com', '$2a$10$OWTmnPjs4sDbJIEmId57ferf9incJ3J5OWlS3eBPWjkPvfJB2vBbO', 'Evgeny', '1970-01-01',
        now());

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO theme (id, name, image_url)
VALUES (1, 'IT and Stephen King Universe', 'https://images-na.ssl-images-amazon.com/images/I/71tFhdcC0XL.jpg'),
       (2, 'Dragon Age', 'https://upload.wikimedia.org/wikipedia/ru/c/ce/DAO_rus.jpg'),
       (3, 'Mass Effect',
        'https://upload.wikimedia.org/wikipedia/ru/thumb/c/c5/MassEffect_Xbox.jpg/261px-MassEffect_Xbox.jpg'),
       (4, 'Metal Gear',
        'https://s2.gaming-cdn.com/images/products/7900/orig/metal-gear-solid-remake-remake-edition-pc-game-steam-cover.jpg'),
       (5, 'Dark Souls', 'https://cdn-products.eneba.com/resized-products/BjdEY6u_350x200_1x-0.jpg'),
       (6, 'The Witcher',
        'https://img.g2a.com/323x433/1x1x0/the-witcher-3-wild-hunt-goty-edition-gogcom-key-global/59108976ae653aa55c6ac1f2'),
       (7, 'Bloodborne', 'https://metarankings.ru/wp-content/uploads/bloodborne-box-art-cover.jpg'),
       (8, 'Doom', 'https://s2.gaming-cdn.com/images/products/865/orig/game-steam-doom-cover.jpg'),
       (9, 'TES', 'https://upload.wikimedia.org/wikipedia/ru/e/ec/The_Elder_Scrolls_III_Morrowind_box_art.jpg'),
       (10, 'Game of thrones', 'https://m.media-amazon.com/images/I/51-9Aez4ewL._AC_SY580_.jpg');

INSERT INTO publication (name, description, creation_date, user_id, theme_id)
VALUES ('Another one story about st.Mann', 'Small talk on the bus station', '2019-01-01', 1, 1),
       ('Test publication 2', 'Test Publication description 2', '2022-02-04', 1, 1),
       ('Test publication 3', 'Test Publication description 3', '2022-02-04', 1, 1),
       ('Test publication 4', 'Test Publication description 4', '2022-02-04', 1, 1),
       ('Test publication 5', 'Test Publication description 5', '2022-02-04', 1, 1),
       ('Test publication 6', 'Test Publication description 6', '2022-02-04', 1, 1),
       ('Test publication 7', 'Test Publication description 7', '2022-02-04', 1, 1),
       ('Test publication 8', 'Test Publication description 8', '2022-02-04', 1, 1),
       ('Test publication 9', 'Test Publication description 9', '2022-02-04', 1, 1),
       ('Test publication 10', 'Test Publication description 10', '2022-02-04', 1, 1),
       ('Test publication 11', 'Test Publication description 11', '2022-02-04', 1, 1),
       ('Test publication 12', 'Test Publication description 12', '2022-02-04', 1, 1),
       ('Test publication 13', 'Test Publication description 13', '2022-02-04', 1, 1),
       ('Test publication 14', 'Test Publication description 14', '2022-02-04', 1, 1),
       ('Test publication 15', 'Test Publication description 15', '2022-02-04', 1, 1),
       ('Test publication 16', 'Test Publication description 16', '2022-02-04', 1, 1),
       ('Test publication 17', 'Test Publication description 17', '2022-02-04', 1, 1),
       ('Test publication 18', 'Test Publication description 18', '2022-02-04', 1, 1),
       ('Test publication 19', 'Test Publication description 19', '2022-02-04', 1, 1),
       ('Test publication 20', 'Test Publication description 20', '2022-02-04', 1, 1),
       ('Test publication 21', 'Test Publication description 21', '2022-02-04', 1, 1),
       ('Test publication 22', 'Test Publication description 22', '2022-02-04', 1, 1),
       ('Test publication 23', 'Test Publication description 23', '2022-02-04', 1, 1),
       ('Test publication 24', 'Test Publication description 24', '2022-02-04', 1, 1),
       ('Test publication 25', 'Test Publication description 25', '2022-02-04', 1, 1),
       ('Test publication 26', 'Test Publication description 26', '2022-02-04', 1, 1),
       ('Test publication 27', 'Test Publication description 27', '2022-02-04', 1, 1),
       ('Test publication 28', 'Test Publication description 28', '2022-02-04', 1, 1),
       ('Test publication 29', 'Test Publication description 29', '2022-02-04', 1, 1),
       ('Test publication 30', 'Test Publication description 30', '2022-02-04', 1, 1),
       ('Test publication 31', 'Test Publication description 31', '2022-02-04', 1, 1),
       ('Test publication 32', 'Test Publication description 32', '2022-02-04', 1, 1),
       ('Test publication 33', 'Test Publication description 33', '2022-02-04', 1, 1),
       ('Test publication 34', 'Test Publication description 34', '2022-02-04', 1, 1),
       ('Test publication 35', 'Test Publication description 35', '2022-02-04', 1, 1),
       ('Test publication 36', 'Test Publication description 36', '2022-02-04', 1, 1),
       ('Test publication 37', 'Test Publication description 37', '2022-02-04', 1, 1),
       ('Test publication 38', 'Test Publication description 38', '2022-02-04', 1, 1),
       ('Test publication 39', 'Test Publication description 39', '2022-02-04', 1, 1),
       ('Test publication 40', 'Test Publication description 40', '2022-02-04', 1, 1),
       ('Test publication 42', 'Test Publication description 42', '2022-02-04', 1, 1),
       ('Test publication 43', 'Test Publication description 43', '2022-02-04', 1, 1),
       ('Test publication 44', 'Test Publication description 44', '2022-02-04', 1, 1),
       ('Test publication 45', 'Test Publication description 45', '2022-02-04', 1, 1),
       ('Test publication 46', 'Test Publication description 46', '2022-02-04', 1, 1),
       ('Test publication 47', 'Test Publication description 47', '2022-02-04', 1, 1),
       ('Test publication 48', 'Test Publication description 48', '2022-02-04', 1, 1),
       ('Test publication 49', 'Test Publication description 49', '2022-02-04', 1, 1),
       ('Test publication 50', 'Test Publication description 50', '2022-02-04', 1, 1),
       ('Test publication 51', 'Test Publication description 51', '2022-02-04', 1, 1),
       ('Test publication 52', 'Test Publication description 52', '2022-02-04', 1, 1),
       ('Test publication 53', 'Test Publication description 53', '2022-02-04', 1, 1),
       ('Test publication 54', 'Test Publication description 54', '2022-02-04', 1, 1),
       ('Test publication 55', 'Test Publication description 55', '2022-02-04', 1, 1),
       ('Test publication 56', 'Test Publication description 56', '2022-02-04', 1, 1),
       ('Test publication 57', 'Test Publication description 57', '2022-02-04', 1, 1),
       ('Test publication 58', 'Test Publication description 58', '2022-02-04', 1, 1),
       ('Test publication 59', 'Test Publication description 59', '2022-02-04', 1, 1),
       ('Test publication 60', 'Test Publication description 60', '2022-02-04', 1, 1),
       ('The Witcher 3',
        'The Witcher 3 is a story-driven, next-generation open world role-playing game set in a visually stunning fantasy' ||
        ' universe full of meaningful choices and impactful consequences.', '2019-01-01', 1, 6);



INSERT INTO chapter (image_url, name, text, publication_id)
VALUES (null, 'Sometimes i liked to take a nap in public places',
        'Once I, passing by the diner that was next to the fair ...', 1);

INSERT INTO tag (name)
VALUES ('Scary'),
       ('RPG'),
       ('Action'),
       ('Adventure'),
       ('Strategy'),
       ('FPS'),
       ('MMO'),
       ('MMORPG'),
       ('RTS'),
       ('MOBA'),
       ('Simulator'),
       ('Fantasy'),
       ('Horror'),
       ('Fantastic'),
       ('Sci-fi'),
       ('Stephen King');

INSERT INTO publication_tag (publication_id, tag_id)
VALUES (1, 12),
       (1, 13),
       (1, 14),
       (1, 16),
       (1, 1),
       (2, 1),
       (60, 2),
       (60, 3),
       (60, 11);

INSERT INTO bookmarks (user_id, publication_id)
VALUES (1, 1),
       (2, 1);

INSERT INTO likes (user_id, publication_id)
VALUES (1, 1);

INSERT INTO preferences (user_id, theme_id)
VALUES (1, 1),
       (2, 1),
       (1, 6);



