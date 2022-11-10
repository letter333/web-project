DELIMITER $$
create procedure delete_user (
    param_user_id varchar(30)
)
BEGIN

update feed set feed_user_id = '탈퇴한 회원' where feed_user_id = param_user_id;
update comment set comment_user_id = '탈퇴한 회원' where comment_user_id = param_user_id;
delete from profile_table where profile_user_id = param_user_id;
delete from user where user_id = param_user_id;
END $$