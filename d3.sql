drop table cafe_susunggu;
drop table cafe_segu;

create table cafe_segu(
cafenum int primary key auto_increment not null,
category varchar(450),
name varchar(450),
address_new varchar(450),
address_old varchar(450),
area float,
callnum varchar(450),
bname varchar(450)
);

create table cafe_susunggu(
cafenum int primary key auto_increment not null,
category varchar(450),
name varchar(450),
address_new varchar(450),
address_old varchar(450),
area float,
callnum varchar(450),
bname varchar(450)
);

select * from cafe_segu;
select * from cafe_susunggu;
