<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="JDBC.model.User" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <h2>Chào mừng đến trang chủ</h2>

    <%
        User user = (User) session.getAttribute("account");
        if (user != null) {
    %>
        <p>Xin chào, <b><%= user.getUserName() %></b>!</p>
        <a href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
    <%
        } else {
    %>
        <p>Bạn chưa đăng nhập. <a href="<%= request.getContextPath() %>/login">Đăng nhập</a></p>
    <%
        }
    %>
</body>
</html>
