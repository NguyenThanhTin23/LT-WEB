<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<h2>Update Profile</h2>
<form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
    Fullname: <input type="text" name="fullname" value="${user.fullname}"/><br/>
    Phone: <input type="text" name="phone" value="${user.phone}"/><br/>
    Current Image: <br/>
    <img src="${pageContext.request.contextPath}/images/${user.image}" width="100"/><br/>
    New Image: <input type="file" name="image"/><br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
