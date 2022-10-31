package com.project.sns.dao;

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
}
