drop table comment;
drop table feed;
drop table user;
drop table file_table;

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
feed_last_feed_id int default 1,
feed_created_at datetime not null default now()
);

create table file_table (
file_num int auto_increment primary key,
original_name varchar(255) not null,
file_name varchar(255) not null,
file_size int,
file_save_path varchar(255),
feed_id int
);

create table comment (
comment_id int auto_increment primary key,
comment_feed_id int not null,
comment_user_id varchar(30) not null,
comment_content text not null,
comment_created_at datetime default now()
);

