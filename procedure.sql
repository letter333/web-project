DELIMITER $$
create procedure insert_comment (
	param_comment_feed_id int,
    param_comment_user_id varchar(30),
    param_comment_content text
)
BEGIN

insert into comment(comment_feed_id, comment_user_id, comment_content) values (param_comment_feed_id, param_comment_user_id, param_comment_content);
update feed set feed_comment_count = feed_comment_count + 1 where feed_id = param_comment_feed_id;
END $$