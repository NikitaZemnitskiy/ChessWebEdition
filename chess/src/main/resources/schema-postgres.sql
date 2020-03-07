--create table if not exists users(id SERIAL NOT NULL primary key, username varchar(50) not null,password varchar(500) not null, enabled boolean not null);
--create table if not exists games (id SERIAL NOT NULL primary key, white_player varchar(50) not null, black_player varchar(50) not null,board varchar(100) not null, is_end boolean not null);
--create table if not exists authorities (username varchar(50) not null,authority varchar(50) not null);
--create unique index if not exists ix_auth_username on authorities (username,authority);


/*
create table if not exists users(
id SERIAL NOT NULL primary key,
username varchar(50) not null,
password varchar(500) not null,
enabled boolean not null
);


create table if not exists game (
	id SERIAL NOT NULL primary key,
	white_player_id integer not null REFERENCES users (id),
	black_player_id integer not null REFERENCES users (id),
	board varchar(100) not null,
	created int,
	status game_status not null
);




create table if not exists turn (
	id SERIAL NOT NULL primary key,
	turn varchar(4) NOT NULL,
	game_id integer not null REFERENCES game (id)
);

CREATE TYPE game_status AS ENUM ('waitingForPlayers', 'isGoing', 'blackWin', 'whiteWin', 'draw');
*/
--SELECT * FROM games AS c LEFT JOIN users AS u ON(c.white_player_id=u.id);


