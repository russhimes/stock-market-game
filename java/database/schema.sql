BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS games CASCADE;
DROP TABLE IF EXISTS game_players CASCADE;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE games (
        id serial PRIMARY KEY,
        name varchar(50) NOT NULL,
        organizer_id int NOT NULL,
        end_date date NOT NULL,
        end_time time NOT NULL,
        
        CONSTRAINT FK_games_users FOREIGN KEY(organizer_id) REFERENCES users(user_id)
);

CREATE TABLE game_players (
        id serial PRIMARY KEY,
        user_id int NOT NULL,
        game_id int NOT NULL,
        available_funds decimal(15, 2) NOT NULL,
        game_status varchar(50) NOT NULL,
        
        CONSTRAINT FK_game_players_users FOREIGN KEY(user_id) REFERENCES users(user_id),
        CONSTRAINT FK_game_players_games FOREIGN KEY(game_id) REFERENCES games(id)
);

COMMIT TRANSACTION;
