<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Quên mật khẩu</title>
</head>
<body>
    <h2>Quên mật khẩu</h2>

    <!-- Hiển thị thông báo -->
    <c:if test="${not empty alert}">
        <p style="color:red">${alert}</p>
    </c:if>

    <!-- Bước 1: nhập email -->
    <c:if test="${empty step or step == '1'}">
        <form action="${pageContext.request.contextPath}/forgot" method="post">
            <input type="hidden" name="step" value="1" />
            <label>Email:</label>
            <input type="text" name="email" required />
            <button type="submit">Tiếp tục</button>
        </form>
    </c:if>

    <!-- Bước 2: nhập mật khẩu mới -->
    <c:if test="${step == '2'}">
        <form action="${pageContext.request.contextPath}/forgot" method="post">
            <input type="hidden" name="step" value="2" />
            <input type="hidden" name="email" value="${email}" />
            <label>Mật khẩu mới:</label>
            <input type="password" name="newPassword" required />
            <button type="submit">Đổi mật khẩu</button>
        </form>
    </c:if>

    <!-- Bước 3: hoàn tất -->
    <c:if test="${step == 'done'}">
        <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
    </c:if>
</body>
</html>
