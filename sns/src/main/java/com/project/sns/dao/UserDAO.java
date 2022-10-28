package com.project.sns.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int userInsert(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("user.user_insert", map);
	}
}
