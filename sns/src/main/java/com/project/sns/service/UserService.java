package com.project.sns.service;

import java.util.Map;

import com.project.sns.dto.LoginDTO;
import com.project.sns.dto.UserDTO;

public interface UserService {

	String createUser(UserDTO dto);

	String login(LoginDTO dto);

	int checkJoin(String user_id);

	UserDTO getUser(String user_id);

	String checkPw(UserDTO dto);

	int modifyUser(UserDTO dto);

	String getProfileImg(UserDTO dto);

	void updateProfile(String originalFileName, String saveFileName, String user_id);

	int initProfile(UserDTO dto);

	int deleteUser(String user_id);

}
