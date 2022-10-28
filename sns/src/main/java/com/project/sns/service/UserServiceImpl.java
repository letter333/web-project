package com.project.sns.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sns.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;
	
	@Override
	public String createUser(Map<String, Object> map) {
		int affectRowCount = this.userDao.userInsert(map);
		if(affectRowCount == 1) {
			return map.get("user_id").toString();
		}
		return null;
	}
}
