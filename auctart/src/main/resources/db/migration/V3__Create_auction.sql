create table auction
(
    id bigint auto_increment,
    name text null,
    author text null,
    start_date datetime not null,
    end_date datetime not null,
    start_price int not null,
    user bigint not null,
    constraint auction_pk
        primary key (id),
    constraint auction_user_id_fk
        foreign key (user) references user (id)
);

