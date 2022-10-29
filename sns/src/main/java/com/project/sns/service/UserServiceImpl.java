package com.project.sns.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sns.dao.UserDAO;
import com.project.sns.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;
	
	@Override
	public String createUser(UserDTO dto) {
		int affectRowCount = this.userDao.userInsert(dto);
		if(affectRowCount == 1) {
			return dto.getUser_id();
		}
		return null;
	}
}
