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

