<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>User Management</h2>

<form action="${pageContext.request.contextPath}/admin/users/search" method="get" style="margin-bottom: 20px;">
    <input type="text" name="keyword" value="${keyword}" placeholder="Search username or email...">
    <button type="submit">Search</button>
</form>
<hr>

<h3>Add New User</h3>
<form action="${pageContext.request.contextPath}/admin/users/create" method="post">
    <div class="form-group"><label>Username:</label> <input type="text" name="username" required></div>
    <div class="form-group"><label>Password:</label> <input type="password" name="password" required></div>
    <div class="form-group"><label>Email:</label> <input type="email" name="email" required></div>
    <div class="form-group"><label>Admin?:</label> <input type="checkbox" name="isAdmin" value="true"></div>
    <button type="submit">Create User</button>
</form>
<hr>

<h3>User List</h3>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.isAdmin ? 'Admin' : 'User'}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.userId}">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/users/delete?id=${user.userId}" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>