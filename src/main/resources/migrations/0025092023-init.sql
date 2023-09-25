drop table if exists rooms cascade;
drop table if exists users cascade;
drop table if exists cards cascade;
drop table if exists games cascade;
drop table if exists teams cascade;
drop table if exists media cascade;
drop table if exists players cascade;
drop table if exists playercards cascade;


drop sequence if exists rooms_sequence;
drop sequence if exists users_sequence;
drop sequence if exists cards_sequence;
drop sequence if exists players_sequence;
drop sequence if exists playercards_sequence;
drop sequence if exists games_sequence;
drop sequence if exists teams_sequence;
drop sequence if exists media_sequence;

create sequence rooms_sequence start with 1 increment by 1;
create sequence users_sequence start with 1 increment by 1;
create sequence cards_sequence start with 1 increment by 1;
create sequence players_sequence start with 1 increment by 1;
create sequence playercards_sequence start with 1 increment by 1;
create sequence games_sequence start with 1 increment by 1;
create sequence teams_sequence start with 1 increment by 1;
create sequence media_sequence start with 1 increment by 1;


create table users(
	id bigint not null primary key,
	firstname varchar(30) not null,
	surname varchar(30) not null,
	nickname varchar(30) not null
);


create table rooms(
	id bigint not null primary key,
	code varchar(30) not null
);

create table teams(
	id bigint not null primary key,
	totalscore bigint not null default 0,
	points bigint not null deafult 0,
	room_id bigint not null 
);


create table games(
	id bigint not null primary key,
	winner_team_id bigint not null,
	room_id bigint not null
);


create table playercards(
	id bigint not null primary key,
	player_id bigint not null,
	card_id bigint not null,
	room_id bigint,
	game_id bigint
);


create table cards(
	id bigint not null primary key,
	value varchar(30) not null,
	suit varchar(30) not null,
	rating bigint not null
);

create table media(
	id bigint not null primary key,
	title varchar(50) not null,
	stype varchar(50) not null,
	data bytea not null,
	user_id bigint
);

create table players(
	id bigint not null primary key,
	alone boolean default false,
	cuatrola boolean default false,
	shouted_twenty int default 0,
	shouted_forty boolean default false,
	user_id int not null
);





alter table if exists players 
				ADD CONSTRAINT fk_player_user_reference 
				FOREIGN KEY(user_id) 
				references users (id) on delete restrict on update restrict;

alter table if exists teams 
				ADD CONSTRAINT fk_teams_room_reference 
				FOREIGN KEY(room_id) 
				references rooms (id) on delete restrict on update restrict;


alter table if exists games 
				ADD CONSTRAINT fk_games_room_reference 
				FOREIGN KEY(room_id) 
				references games (id) on delete restrict on update restrict;

alter table if exists media 
	ADD CONSTRAINT fk_users_media_reference 
	FOREIGN KEY(user_id) 
	REFERENCES
	users (id) on delete restrict on update restrict;


alter table if exists playercards 
	ADD CONSTRAINT fk_player_reference 
	FOREIGN KEY(player_id) 
	REFERENCES
	users (id) on delete restrict on update restrict;

alter table if exists playercards 
	ADD CONSTRAINT fk_card_reference 
	FOREIGN KEY(card_id) 
	REFERENCES
	cards (id) on delete restrict on update restrict;


alter table if exists playercards 
	ADD CONSTRAINT fk_room_reference 
	FOREIGN KEY(room_id) 
	REFERENCES
	rooms (id) on delete restrict on update restrict;


alter table if exists playercards 
	ADD CONSTRAINT fk_game_reference 
	FOREIGN KEY(game_id) 
	REFERENCES
	games (id) on delete restrict on update restrict;








