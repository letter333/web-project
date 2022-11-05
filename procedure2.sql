DELIMITER $$
create procedure delete_comment (
	param_comment_id int,
    param_comment_feed_id int
)
BEGIN

update feed set feed_comment_count = feed_comment_count - 1 where feed_id = param_comment_feed_id;
delete from comment where comment_id = param_comment_id;
END $$	