DELIMITER $$
create procedure new_feed (
    param_feed_content text,
    param_feed_user_id varchar(30)
)
BEGIN

update feed set feed_last_feed_id = feed_last_feed_id + 1;
insert into feed(feed_content, feed_user_id) values (param_feed_content, param_feed_user_id);
END $$