package JDBC.service.impl;

import JDBC.model.User;
import JDBC.service.UserService;
import JDBC.dao.impl.*;
import JDBC.dao.*;
public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
	User user = this.get(username);
	if (user != null && password.equals(user.getPassWord())) {
	return user;
	}
	return null;
	}

	@Override
	public User get(String username) {
	return userDao.get(username);
	}
}
