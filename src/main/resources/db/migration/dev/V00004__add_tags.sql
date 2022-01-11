ALTER TABLE publication
    ADD
        creation_date date default current_date NOT NULL;

INSERT INTO tag (name)
VALUES ('RPG'),
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

INSERT INTO publication (name, description, creation_date, user_id, theme_id)
VALUES ('The Witcher 3',
        'The Witcher 3 is a story-driven, next-generation open world role-playing game set in a visually stunning fantasy' ||
        ' universe full of meaningful choices and impactful consequences.', '2019-01-01', 1, 6);

INSERT INTO publication_tag (publication_id, tag_id)
VALUES (1, 12),
       (1, 13),
       (1, 14),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 11);
