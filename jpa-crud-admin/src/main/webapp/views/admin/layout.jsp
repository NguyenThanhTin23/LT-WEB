<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f7f6; }
        .header { background-color: #333; color: white; padding: 15px 25px; }
        .header h1 { margin: 0; }
        .container { display: flex; }
        .nav { width: 220px; background-color: #fdfdfd; border-right: 1px solid #e0e0e0; min-height: 90vh; padding-top: 20px; }
        .nav h3 { padding-left: 20px; margin-top: 0; }
        .nav ul { list-style: none; padding: 0; margin: 0; }
        .nav ul li a { display: block; padding: 12px 20px; text-decoration: none; color: #333; border-bottom: 1px solid #eee; }
        .nav ul li a:hover { background-color: #f0f0f0; }
        .content { flex-grow: 1; padding: 25px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f8f8f8; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>

    <div class="header">
        <h1>Admin Control Panel</h1>
    </div>

    <div class="container">
        <div class="nav">
            <h3>Menu</h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/users">Manage Users</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/categories">Manage Categories</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/videos">Manage Videos</a></li>
            </ul>
        </div>
        <div class="content">
            <jsp:include page="${view}" />
        </div>
    </div>

</body>
</html>