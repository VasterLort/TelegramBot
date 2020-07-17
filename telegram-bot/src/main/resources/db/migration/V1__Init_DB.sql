create sequence hibernate_sequence start 1 increment 1;
create table city (
    id int8 not null,
    info varchar(255),
    name varchar(255), primary key (id)
);