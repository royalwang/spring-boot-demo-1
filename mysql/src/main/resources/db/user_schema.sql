drop table if exists user;

create table user(
    id int unsigned auto_increment,
    name varchar(20) not null,
    username varchar(20),
    primary key (id)
);


