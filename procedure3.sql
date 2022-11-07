DELIMITER $$
create procedure delete_file (
    param_feed_id int
)
BEGIN

delete from file_table where feed_id = param_feed_id;
delete from feed where feed_id = param_feed_id;
END $$	