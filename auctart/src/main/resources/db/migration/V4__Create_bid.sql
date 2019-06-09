create table bid
(
    id bigint auto_increment,
    user bigint not null,
    auction bigint not null,
    price int not null,
    constraint bid_pk
        primary key (id),
    constraint bid_auction_id_fk
        foreign key (auction) references auction (id),
    constraint bid_user_id_fk
        foreign key (user) references user (id)
);

