<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Edit Video: ${video.title}</h2>
<form action="${pageContext.request.contextPath}/admin/videos/update" method="post">
    <input type="hidden" name="videoId" value="${video.videoId}">
    <div class="form-group">
        <label>Title</label>
        <input type="text" name="title" value="${video.title}" required>
    </div>
    <div class="form-group">
        <label>Poster URL</label>
        <input type="text" name="poster" value="${video.poster}">
    </div>
    <div class="form-group">
        <label>Views</label>
        <input type="number" name="views" value="${video.views}">
    </div>
    <div class="form-group">
        <label>Description</label>
        <textarea name="description" rows="4">${video.description}</textarea>
    </div>
    <div class="form-group">
        <label>Category</label>
        <select name="categoryId" required>
            <c:forEach var="cat" items="${categories}">
                <option value="${cat.cateId}" ${cat.cateId == video.category.cateId ? 'selected' : ''}>
                    ${cat.cateName}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>Uploader</label>
        <select name="userId" required>
            <c:forEach var="user" items="${users}">
                <option value="${user.userId}" ${user.userId == video.user.userId ? 'selected' : ''}>
                    ${user.username}
                </option>
            </c:forEach>
        </select>
    </div>
     <div class="form-group">
        <label>Is Active?</label>
        <input type="checkbox" name="isActive" value="true" ${video.isActive ? 'checked' : ''}>
    </div>
    <button type="submit">Update Video</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/admin/videos">Back to list</a>