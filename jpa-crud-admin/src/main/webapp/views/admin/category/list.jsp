<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Category Management</h2>

<form action="${pageContext.request.contextPath}/admin/categories/search" method="get" style="margin-bottom: 20px;">
    <input type="text" name="keyword" value="${keyword}" placeholder="Search by name...">
    <button type="submit">Search</button>
</form>

<hr>

<h3>Add New Category</h3>
<form action="${pageContext.request.contextPath}/admin/categories/create" method="post">
    <div class="form-group">
        <label>Category Name</label>
        <input type="text" name="cateName" required>
    </div>
    <button type="submit">Create</button>
</form>

<hr>

<h3>Category List</h3>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="cat" items="${categories}">
            <tr>
                <td>${cat.cateId}</td>
                <td>${cat.cateName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/categories/edit?id=${cat.cateId}">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/categories/delete?id=${cat.cateId}" onclick="return confirm('Are you sure you want to delete this category?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>