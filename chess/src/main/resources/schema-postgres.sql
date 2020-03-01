create table if not exists users(id SERIAL NOT NULL primary key, username varchar(50) not null,password varchar(500) not null, enabled boolean not null);
--create table if not exists authorities (username varchar(50) not null,authority varchar(50) not null);
--create unique index if not exists ix_auth_username on authorities (username,authority);