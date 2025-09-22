<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Edit Category: ${category.cateName}</h2>

<form action="${pageContext.request.contextPath}/admin/categories/update" method="post">
    <input type="hidden" name="cateId" value="${category.cateId}">

    <div class="form-group">
        <label>Category Name</label>
        <input type="text" name="cateName" value="${category.cateName}" required>
    </div>

    <button type="submit">Update Category</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/admin/categories">Back to list</a>