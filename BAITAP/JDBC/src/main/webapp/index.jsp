<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Upload Demo</title>
</head>
<body>
    <h2>Upload File với Servlet Multipart</h2>
    <form method="post" action="${pageContext.request.contextPath}/multiPartServlet" enctype="multipart/form-data">
        Chọn file: <input type="file" name="file" />
        <input type="submit" value="Upload" />
    </form>
</body>
</html>
