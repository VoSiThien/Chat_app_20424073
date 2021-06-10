create database qlchat;
use qlchat;

create table users
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	username varchar(200),
	password varchar(200)
)
insert into users(username, password) 
values ('user1', '123'),
		('user2', '123')
