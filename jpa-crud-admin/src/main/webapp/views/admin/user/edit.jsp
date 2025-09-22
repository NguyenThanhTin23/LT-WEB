<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Edit User: ${user.username}</h2>
<form action="${pageContext.request.contextPath}/admin/users/update" method="post">
    <input type="hidden" name="userId" value="${user.userId}">

    <div class="form-group">
        <label>Username</label>
        <input type="text" name="username" value="${user.username}" required>
    </div>
    <div class="form-group">
        <label>Password (Leave blank if you don't want to change)</label>
        <input type="password" name="password">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" name="email" value="${user.email}" required>
    </div>
    <div class="form-group">
        <label>Is Admin?</label>
        <input type="checkbox" name="isAdmin" value="true" ${user.isAdmin ? 'checked' : ''}>
    </div>
    <button type="submit">Update User</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/admin/users">Back to list</a>