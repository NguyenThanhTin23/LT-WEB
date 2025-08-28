package JDBC.dao;

import JDBC.model.User;
import JDBC.service.UserService;

public interface UserDao {

	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
