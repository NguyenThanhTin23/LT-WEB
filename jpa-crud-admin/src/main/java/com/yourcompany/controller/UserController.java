package com.yourcompany.controller;

import com.yourcompany.dao.UserDao;
import com.yourcompany.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.List;

@WebServlet({
    "/admin/users",
    "/admin/users/create",
    "/admin/users/update",
    "/admin/users/delete",
    "/admin/users/edit",
    "/admin/users/search"
})
public class UserController extends HttpServlet {
    private UserDao userDao;

    public UserController() {
        this.userDao = new UserDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/users":
                doGetIndex(req, resp);
                break;
            case "/admin/users/delete":
                doGetDelete(req, resp);
                break;
            case "/admin/users/edit":
                doGetEdit(req, resp);
                break;
            case "/admin/users/search":
                doGetSearch(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/users/create":
                doPostCreate(req, resp);
                break;
            case "/admin/users/update":
                doPostUpdate(req, resp);
                break;
        }
    }

    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.findAll();
        req.setAttribute("users", users);
        req.setAttribute("view", "/views/admin/user/list.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("view", "/views/admin/user/edit.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }

    private void doGetSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<User> users = userDao.findByUsernameOrEmail(keyword);
        req.setAttribute("users", users);
        req.setAttribute("keyword", keyword);
        req.setAttribute("view", "/views/admin/user/list.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doPostCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}