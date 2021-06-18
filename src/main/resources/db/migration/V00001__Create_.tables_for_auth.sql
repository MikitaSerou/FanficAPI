create table role
(
    id   serial       not null
        constraint role_pkey
            primary key,
    name varchar(255) not null
);

alter table role
    owner to "user";

INSERT INTO public.role (name)
VALUES ('ROLE_USER');
INSERT INTO public.role (name)
VALUES ('ROLE_ADMIN');
create table users
(
    id       bigserial not null
        constraint users_pkey
            primary key,
    email    varchar(50) unique not null,
    password varchar(120) not null,
    username varchar(20) unique not null
);

alter table users
    owner to "user";

INSERT INTO public.users (email, password, username)
VALUES ('miita.serou@gmail.com', '$2a$10$OWTmnPjs4sDbJIEmId57ferf9incJ3J5OWlS3eBPWjkPvfJB2vBbO', 'Mikita');

create table user_roles
(
    user_id bigint   not null
            references users,
    role_id smallint not null
            references role,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

alter table user_roles
    owner to "user";

INSERT INTO public.user_roles (user_id, role_id)
VALUES (1, 1);