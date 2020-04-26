-- auto-generated definition
create table "AppUser"
(
    id        varchar not null
        constraint user_pk
            primary key,
    firstname varchar not null,
    lastname  varchar not null,
    email     varchar not null,
    password  varchar not null,
    avatarurl varchar not null
);

alter table "AppUser"
    owner to euvsvirus;

-- auto-generated definition
create table "Token"
(
    userid varchar not null
        constraint token_pk
            primary key
        constraint token_user_id_fk
            references "AppUser"
            on update cascade on delete cascade,
    token    varchar not null
);

alter table "Token"
    owner to euvsvirus;


create table "Workout"
(
    id varchar
        constraint workout_pk
            primary key,
    userid varchar not null
        constraint workout_appuser_id_fk
            references "AppUser"
            on update cascade on delete cascade,
    datetimestart timestamptz not null,
    datetimeend timestamptz not null,
    sport varchar not null,
    points varchar[] not null,
    raster varchar[] not null
);

create index workout_userid_index
    on "Workout" (userid);