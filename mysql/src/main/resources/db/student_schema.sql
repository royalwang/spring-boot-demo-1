drop table if exists student;

create table student(
    id int unsigned auto_increment,
    name varchar(20) not null,
    username varchar(20) not null,
    age int,
    email varchar(50),
    primary key (id)
);


