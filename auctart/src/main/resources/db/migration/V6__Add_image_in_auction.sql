alter table auction
    add image bigint not null;

alter table auction
    add constraint auction_image_id_fk
        foreign key (image) references image (id);
