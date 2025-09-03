package JDBC.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import JDBC.service.UserService;
import JDBC.service.impl.UserServiceImpl;

@WebServlet("/forgot")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String step = req.getParameter("step");

        // Bước 1: nhập email
        if ("1".equals(step)) {
            String email = req.getParameter("email");

            if (userService.checkEmailExist(email)) {
                req.setAttribute("email", email);
                req.setAttribute("step", "2"); // sang bước nhập mật khẩu mới
            } else {
                req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
                req.setAttribute("step", "1");
            }
            req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
        }

        // Bước 2: nhập mật khẩu mới
        else if ("2".equals(step)) {
            String email = req.getParameter("email");
            String newPass = req.getParameter("newPassword");

            if (newPass != null && !newPass.trim().isEmpty()) {
                userService.updatePasswordByEmail(email, newPass);
                req.setAttribute("alert", "Mật khẩu đã được đổi thành công! Vui lòng đăng nhập.");
                req.setAttribute("step", "done");
            } else {
                req.setAttribute("alert", "Mật khẩu mới không được để trống!");
                req.setAttribute("email", email);
                req.setAttribute("step", "2");
            }
            req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
        }
    }
}
