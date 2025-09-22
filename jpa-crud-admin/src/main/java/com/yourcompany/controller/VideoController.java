package com.yourcompany.controller;

import com.yourcompany.dao.CategoryDao;
import com.yourcompany.dao.UserDao;
import com.yourcompany.dao.VideoDao;
import com.yourcompany.entity.Category;
import com.yourcompany.entity.User;
import com.yourcompany.entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.List;

@WebServlet({
    "/admin/videos",
    "/admin/videos/create",
    "/admin/videos/update",
    "/admin/videos/delete",
    "/admin/videos/edit",
    "/admin/videos/search"
})
public class VideoController extends HttpServlet {
    private VideoDao videoDao;
    private UserDao userDao;
    private CategoryDao categoryDao;

    public VideoController() {
        this.videoDao = new VideoDao();
        this.userDao = new UserDao();
        this.categoryDao = new CategoryDao();
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
            case "/admin/videos":
                doGetIndex(req, resp);
                break;
            case "/admin/videos/delete":
                doGetDelete(req, resp);
                break;
            case "/admin/videos/edit":
                doGetEdit(req, resp);
                break;
             case "/admin/videos/create": // Hiển thị form create
                doGetCreate(req, resp);
                break;
            case "/admin/videos/search":
                doGetSearch(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/videos/create":
                doPostCreate(req, resp);
                break;
            case "/admin/videos/update":
                doPostUpdate(req, resp);
                break;
        }
    }

    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videos = videoDao.findAll();
        req.setAttribute("videos", videos);
        req.setAttribute("view", "/views/admin/video/list.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

     private void doGetCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.findAll();
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("users", users);
        req.setAttribute("categories", categories);
        req.setAttribute("view", "/views/admin/video/create.jsp"); // Form tạo mới
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.findAll();
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("users", users);
        req.setAttribute("categories", categories);

        int id = Integer.parseInt(req.getParameter("id"));
        Video video = videoDao.findById(id);
        req.setAttribute("video", video);

        req.setAttribute("view", "/views/admin/video/edit.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            videoDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }

    private void doGetSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Video> videos = videoDao.findByTitle(keyword);
        req.setAttribute("videos", videos);
        req.setAttribute("keyword", keyword);
        req.setAttribute("view", "/views/admin/video/list.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    private void doPostCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Video video = new Video();
            BeanUtils.populate(video, req.getParameterMap());

            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            video.setCategory(categoryDao.findById(categoryId));
            video.setUser(userDao.findById(userId));

            videoDao.create(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Video video = new Video();
            BeanUtils.populate(video, req.getParameterMap());

            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            video.setCategory(categoryDao.findById(categoryId));
            video.setUser(userDao.findById(userId));

            videoDao.update(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }
}