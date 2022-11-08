package com.project.sns.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.sns.dao.UserDAO;
import com.project.sns.dto.LoginDTO;
import com.project.sns.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;
	
	@Override
	public String createUser(UserDTO dto) {
		PasswordEncoder p = new BCryptPasswordEncoder();
		dto.setUser_pw(p.encode(dto.getUser_pw()));		
		
		int affectRowCount = this.userDao.userInsert(dto);
		if(affectRowCount == 1) {
			return dto.getUser_id();
		}
		return null;
	}

	@Override
	public String login(LoginDTO dto) {
		return userDao.login(dto);
	}
	
	@Override
	public int checkJoin(String user_id) {
		return userDao.checkJoin(user_id);
	}
	
	@Override
	public UserDTO getUser(String user_id) {
		return userDao.getUser(user_id);
	}
	
	@Override
	public String checkPw(UserDTO dto) {
		return userDao.checkPw(dto);
	}
	
	@Override
	public int modifyUser(UserDTO dto) {
		return userDao.modifyUser(dto);
	}
}
