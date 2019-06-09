create table user
(
	id bigint auto_increment,
	username varchar(20) not null,
	password text not null,
	email varchar(320) not null,
	constraint user_pk
		primary key (id)
);

create unique index user_email_uindex
	on user (email);

create unique index user_username_uindex
	on user (username);

