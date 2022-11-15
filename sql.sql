drop table comment;
drop table feed;
drop table user;
drop table file_table;
drop table profile_table;

create table user(
user_id varchar(30) primary key,
user_pw varchar(255) not null,
user_name varchar(50) not null,
user_gender varchar(10) default '',
user_birth varchar(10) default '',
user_email varchar(50) not null,
user_phone varchar(20) not null,
user_reg_date datetime not null default now()
);

create table feed(
feed_id int auto_increment primary key,
feed_content text not null,
feed_user_id varchar(30) not null,
feed_comment_count int not null default 0,
feed_like_count int not null default 0,
feed_last_feed_id int default 1,
feed_created_at datetime not null default now()
);

create table file_table (
file_num int auto_increment primary key,
original_name varchar(255) not null,
file_name varchar(255) not null,
file_size int,
feed_id int
);

create table comment (
comment_id int auto_increment primary key, 
comment_feed_id int not null,
comment_user_id varchar(30) not null,
comment_content text not null,
comment_created_at datetime default now()
);

create table profile_table (
	profile_user_id varchar(30) not null primary key,
    profile_original_name varchar(255) default '',
    profile_file_name varchar(255) default 'default-image.png'
);

create table feed_like (
	like_num int auto_increment primary key,
    like_feed_id int not null,
    like_user_id varchar(30) not null
);


select * from feed;
select * from comment;
select * from file_table;
select * from user;
select * from profile_table;
select * from feed_like;

insert into profile_table(profile_user_id) values ('test');