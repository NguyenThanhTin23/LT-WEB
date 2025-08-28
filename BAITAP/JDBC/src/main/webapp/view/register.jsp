<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body {
        background: #f4f6f9;
        font-family: Arial, sans-serif;
    }
    .register-box {
        width: 400px;
        margin: 80px auto;
        padding: 30px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background: #fff;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    }
    .register-box h2 {
        text-align: center;
        margin-bottom: 20px;
        font-weight: bold;
    }
    .input-group {
        margin-bottom: 15px;
    }
    .input-group-text {
        background: #fff;
        border-right: 0;
    }
    .form-control {
        border-left: 0;
        height: 45px;
    }
    .btn-primary {
        width: 100%;
        height: 45px;
        font-size: 16px;
        font-weight: bold;
    }
    .text-center {
        margin-top: 15px;
        font-size: 14px;
    }
</style>
</head>
<body>
    <div class="register-box">
        <form action="register" method="post">
            <h2>Tạo tài khoản mới</h2>
            <c:if test="${alert != null}">
                <h3 class="alert alert-danger">${alert}</h3>
            </c:if>

            <!-- Username -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" placeholder="Tài khoản" name="username">
            </div>

            <!-- Fullname -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-id-card"></i></span>
                <input type="text" class="form-control" placeholder="Họ tên" name="fullname">
            </div>

            <!-- Email -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                <input type="email" class="form-control" placeholder="Nhập Email" name="email">
            </div>

            <!-- Phone -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-phone"></i></span>
                <input type="text" class="form-control" placeholder="Số điện thoại" name="phone">
            </div>

            <!-- Password -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" placeholder="Mật khẩu" name="password">
            </div>

            <!-- Confirm Password -->
            <div class="input-group">
                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" placeholder="Nhập lại mật khẩu" name="confirmPassword">
            </div>

            <!-- Button -->
            <button type="submit" class="btn btn-primary">Tạo tài khoản</button>

            <!-- Login Link -->
            <div class="text-center">
                Nếu bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
            </div>
        </form>
    </div>
</body>
</html>
