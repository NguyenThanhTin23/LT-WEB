package JDBC.dao;

import JDBC.model.User;
import JDBC.service.UserService;

public interface UserDao {

	User get(String username);
}
