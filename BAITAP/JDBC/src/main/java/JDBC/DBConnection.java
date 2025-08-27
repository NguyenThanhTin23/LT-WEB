package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private final String serverName = "localhost"; // Tên máy chủ SQL Server
	private final String dbName = "JDBC_LoginDB"; // Tên cơ sở dữ liệu
	private final String portNumber = "1433"; // Cổng mặc định của SQL Server
	private final String instance = ""; // Tên instance (nếu có)
	private final String userID = ""; // User ID (nếu sử dụng SQL Authentication)
	private final String password = ""; // Password (nếu sử dụng SQL Authentication)

	// Kết nối với SQL Server bằng Windows Authentication
	public Connection getConnectionW() throws Exception {
		// URL kết nối với SQL Server, sử dụng Windows Authentication
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";integratedSecurity=true;databaseName="
				+ dbName + ";encrypt=true;trustServerCertificate=true;";

		// Nếu có instance, thêm vào URL kết nối
		if (instance != null && !instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
					+ ";integratedSecurity=true;databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
		}

		// Load SQL Server JDBC driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Kết nối tới SQL Server
		return DriverManager.getConnection(url);
	}

	// Kết nối với SQL Server bằng SQL Server Authentication (sử dụng userID và
	// password)
	public Connection getConnectionSQLAuth() throws Exception {
		// URL kết nối với SQL Server bằng SQL Server Authentication
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";user=" + userID + ";password=" + password
				+ ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";

		// Load SQL Server JDBC driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Kết nối tới SQL Server
		return DriverManager.getConnection(url);
	}

	// Hàm main để kiểm tra kết nối
	public static void main(String[] args) {
	    /*
	    	try {
	            // Test kết nối với Windows Authentication
	            DBConnection dbConn = new DBConnection();
	            Connection conn = dbConn.getConnectionW();

	            // Create statement
	            Statement stmt = conn.createStatement();

	            // Insert 'GiaoVien'
	            stmt.executeUpdate("INSERT INTO Users(id, name, diachi) VALUES (2, 'Trung', 'HCM')");

	            // Get data from table 'Users'
	            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");

	            // Show data
	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("diachi"));
	            }

	            conn.close(); // close connection
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }*/
	    	String sqlInsert = "INSERT INTO Users VALUES(?, ?, ?)";
	    	String selectAll = "SELECT * FROM Users";
	    	try {
	    	// connect to database
	    	DBConnection dbConn = new DBConnection();
		    Connection conn = dbConn.getConnectionW();

	    	// crate statement to insert GiaoVien
	    	PreparedStatement stmt = conn.prepareStatement(sqlInsert);
	    	stmt.setInt(1, 112);
	    	stmt.setString(2, "Tinnn");
	    	stmt.setString(3, "HCM");
	    	stmt.execute();
	    	// select all GiaoVien
	    	stmt = conn.prepareStatement(selectAll);
	    	// get data from table GiaoVien
	    	ResultSet rs = stmt.executeQuery();
	    	// show data
	    	while (rs.next()) {
	    	System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
	    	}
	    	stmt.close();
	    	conn.close();
	    	}
	    	catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
}
