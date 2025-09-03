package JDBC.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.DBConnection;
import JDBC.dao.UserDao;
import JDBC.model.User;
import JDBC.dao.*;

public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM [User] WHERE username = ? ";
		try {
			DBConnection dbConn = new DBConnection();
			Connection conn = dbConn.getConnectionW();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid,phone,createddate) VALUES (?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnectionSQLAuth();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getPassWord());
			ps.setString(5, user.getAvatar());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setDate(8, user.getCreatedDate());
			System.out.println("SQL run: " + ps);
			int rows = ps.executeUpdate();
			System.out.println("Rows affected: " + rows);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkEmailExist(String email) {
	    String sql = "SELECT 1 FROM [User] WHERE LTRIM(RTRIM(email)) = ?";
	    boolean exists = false;

	    try (Connection conn = new DBConnection().getConnectionW(); // hoặc getConnectionSQLAuth()
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        // chuẩn hóa email
	        String emailTrim = email == null ? "" : email.trim();
	        ps.setString(1, emailTrim);

	        System.out.println("🔎 [checkEmailExist] SQL: " + ps);

	        try (ResultSet rs = ps.executeQuery()) {
	            exists = rs.next();
	        }

	        if (exists) {
	            System.out.println("✅ Email tồn tại trong DB: " + emailTrim);
	        } else {
	            System.out.println("❌ Email KHÔNG tồn tại trong DB: " + emailTrim);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exists;
	}



	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [User] where username = ?";
		try {
			conn = new DBConnection().getConnectionW();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public void updatePasswordByEmail(String email, String newPassword) {
	    String sql = "UPDATE [User] SET password=? WHERE email=?";
	    try (Connection conn = new DBConnection().getConnectionW();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, newPassword);
	        ps.setString(2, email);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
}