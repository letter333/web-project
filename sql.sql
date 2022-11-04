create table user(
user_id varchar(30) primary key,
user_pw varchar(255) not null,
user_name varchar(50) not null,
user_gender varchar(10) default '',
user_birth varchar(10) default '',
user_email varchar(50) not null,
user_phone varchar(20) not null,
user_interest varchar(30) default '',
user_reg_date datetime not null default now()
);

drop table user;

select * from user;

create table feed(
feed_id int auto_increment primary key,
feed_title varchar(255) not null,
feed_content text not null,
feed_user_id varchar(30) not null,
feed_created_at datetime not null default now()
);
drop table feed;
select * from feed order by feed_id desc;
select max(feed_id) from feed;

drop table file_table;

create table file_table (
file_num int auto_increment primary key,
original_name varchar(255) not null,
file_name varchar(255) not null,
file_size int,
file_save_path varchar(255),
feed_id int
);

select * from file_table;
select * from file_table where feed_id = 6;
