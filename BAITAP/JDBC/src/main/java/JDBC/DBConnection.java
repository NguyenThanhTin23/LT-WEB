package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private final String serverName = "localhost"; // Tên máy chủ SQL Server
    private final String dbName = "JDBC_LoginDB"; // Tên cơ sở dữ liệu
    private final String portNumber = "1433"; // Cổng mặc định của SQL Server
    private final String instance = ""; // Tên instance (nếu có)
    private final String userID = ""; // User ID (SQL Authentication)
    private final String password = ""; // Password (SQL Authentication)

    // Kết nối với SQL Server bằng Windows Authentication
    public Connection getConnectionW() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";integratedSecurity=true;databaseName=" + dbName
                + ";encrypt=true;trustServerCertificate=true;";

        if (instance != null && !instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                    + ";integratedSecurity=true;databaseName=" + dbName
                    + ";encrypt=true;trustServerCertificate=true;";
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }

    // Kết nối với SQL Server bằng SQL Server Authentication
    public Connection getConnectionSQLAuth() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";user=" + userID + ";password=" + password
                + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }

    // Hàm main để test kết nối và SELECT dữ liệu
    public static void main(String[] args) {
        try {
            DBConnection dbConn = new DBConnection();
            Connection conn = dbConn.getConnectionW(); // Hoặc getConnectionSQLAuth()

            Statement stmt = conn.createStatement();
            String sql = "SELECT TOP (1000) [id], [username], [password], [fullname], [email], [phone], [avatar], [roleid], [createdDate] "
                       + "FROM [JDBC_LoginDB].[dbo].[User]";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("username") + " | " +
                        rs.getString("password") + " | " +
                        rs.getString("fullname") + " | " +
                        rs.getString("email") + " | " +
                        rs.getString("phone") + " | " +
                        rs.getString("avatar") + " | " +
                        rs.getInt("roleid") + " | " +
                        rs.getTimestamp("createdDate")
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
