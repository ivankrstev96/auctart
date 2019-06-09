create table image
(
    id bigint auto_increment,
    name text not null,
    bytes blob not null,
    size bigint not null,
    type text null,
    constraint image_pk
        primary key (id)
);

alter table user
    add image bigint null;

alter table user
    add constraint user_image_id_fk
        foreign key (image) references image (id);