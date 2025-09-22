<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Video Management</h2>
<a href="${pageContext.request.contextPath}/admin/videos/create">Add New Video</a>
<hr>

<form action="${pageContext.request.contextPath}/admin/videos/search" method="get" style="margin-bottom: 20px;">
    <input type="text" name="keyword" value="${keyword}" placeholder="Search by title...">
    <button type="submit">Search</button>
</form>
<hr>

<h3>Video List</h3>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Views</th>
            <th>Category</th>
            <th>Uploader</th>
            <th>Active</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="video" items="${videos}">
            <tr>
                <td>${video.videoId}</td>
                <td>${video.title}</td>
                <td>${video.views}</td>
                <td>${video.category.cateName}</td>
                <td>${video.user.username}</td>
                <td>${video.isActive}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/videos/edit?id=${video.videoId}">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/videos/delete?id=${video.videoId}" onclick="return confirm('Are you sure you want to delete this video?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>