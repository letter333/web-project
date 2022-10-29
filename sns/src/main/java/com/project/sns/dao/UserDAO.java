package com.project.sns.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.sns.dto.UserDTO;

@Repository
public class UserDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int userInsert(UserDTO dto) {
		return this.sqlSessionTemplate.insert("user.user_insert", dto);
	}
}
