package com.project.sns.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.sns.dto.LoginDTO;
import com.project.sns.dto.UserDTO;

@Repository
public class UserDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int userInsert(UserDTO dto) {
		return this.sqlSessionTemplate.insert("user.user_insert", dto);
	}
	
	public String login(LoginDTO dto) {
		return this.sqlSessionTemplate.selectOne("user.login", dto);
	}
	
	public int checkJoin(String user_id) {
		return this.sqlSessionTemplate.selectOne("user.check_join", user_id);
	}
	
	public UserDTO getUser(String user_id) {
		return this.sqlSessionTemplate.selectOne("user.get_user", user_id);
	}
	
	public String checkPw(UserDTO dto) {
		return this.sqlSessionTemplate.selectOne("user.check_pw", dto);
	}
	
	public int modifyUser(UserDTO dto) {
		return this.sqlSessionTemplate.update("user.modify_user", dto);
	}
	
	public String getProfileImg(UserDTO dto) {
		return this.sqlSessionTemplate.selectOne("user.get_profile_img", dto);
	}
	
	public int updateProfile(HashMap<String, Object> map) {
		return this.sqlSessionTemplate.insert("user.update_user_profile", map);
	}
	
	public int initProfile(UserDTO dto) {
		return this.sqlSessionTemplate.insert("user.init_profile", dto);
	}
	
	public int deleteUser(String user_id) {
		return this.sqlSessionTemplate.delete("user.delete_user", user_id);
	}
}
