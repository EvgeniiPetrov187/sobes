create table film
(
    id         bigint auto_increment
        primary key,
    title      varchar(100) null,
    durability int          null
);

create table showtime
(
    id      bigint auto_increment
        primary key,
    time    datetime null,
    film_id bigint   null,
    constraint film_id
        foreign key (film_id) references film (id)
);

create index film_id_idx
    on showtime (film_id);

create table ticket
(
    ticket_id   bigint auto_increment
        primary key,
    price       decimal(19, 2) null,
    showtime_id bigint         null,
    constraint show_id
        foreign key (showtime_id) references showtime (id)
);

create index show_id_idx
    on ticket (price);

create index show_id_idx1
    on ticket (showtime_id);


