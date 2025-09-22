package com.yourcompany.controller;

import com.yourcompany.dao.CategoryDao;
import com.yourcompany.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.List;

/**
 * Đây là Servlet đóng vai trò Controller để xử lý tất cả các yêu cầu CRUD
 * liên quan đến đối tượng Category.
 * Sử dụng annotation @WebServlet để khai báo nhiều URL pattern cho cùng một Servlet.
 */
@WebServlet({
    "/admin/categories",          // URL chính để hiển thị danh sách
    "/admin/categories/create",       // URL để xử lý việc tạo mới
    "/admin/categories/update",       // URL để xử lý việc cập nhật
    "/admin/categories/delete",       // URL để xử lý việc xóa
    "/admin/categories/edit",         // URL để hiển thị form chỉnh sửa
    "/admin/categories/search"        // URL để xử lý tìm kiếm
})
public class CategoryController extends HttpServlet {
    private CategoryDao categoryDao;

    public CategoryController() {
        // Khởi tạo đối tượng DAO để tương tác với database
        this.categoryDao = new CategoryDao();
    }

    /**
     * Ghi đè phương thức service để thiết lập encoding UTF-8 cho tất cả request.
     * Điều này đảm bảo xử lý ký tự tiếng Việt đúng cách.
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }


    /**
     * Xử lý các request HTTP GET.
     * Thường dùng cho các hành động đọc dữ liệu hoặc điều hướng trang.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath(); // Lấy đường dẫn URL mà người dùng yêu cầu
        switch (path) {
            case "/admin/categories":
                doGetIndex(req, resp);
                break;
            case "/admin/categories/delete":
                doGetDelete(req, resp);
                break;
            case "/admin/categories/edit":
                doGetEdit(req, resp);
                break;
            case "/admin/categories/search":
                doGetSearch(req, resp);
                break;
        }
    }

    /**
     * Xử lý các request HTTP POST.
     * Thường dùng cho các hành động gửi dữ liệu từ form lên (tạo mới, cập nhật).
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/categories/create":
                doPostCreate(req, resp);
                break;
            case "/admin/categories/update":
                doPostUpdate(req, resp);
                break;
        }
    }

    /**
     * Lấy danh sách tất cả categories và hiển thị trang list.jsp.
     */
    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
        req.setAttribute("view", "/views/admin/category/list.jsp"); // Đường dẫn đến view con
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp); // Forward đến layout chính
    }

    /**
     * Lấy thông tin của một category theo ID và hiển thị trang edit.jsp.
     */
    private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryDao.findById(id);
            req.setAttribute("category", category);
            req.setAttribute("view", "/views/admin/category/edit.jsp");
            req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            // Xử lý nếu ID không phải là số
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    /**
     * Xử lý hành động xóa category theo ID.
     */
    private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryDao.delete(id);
            // Có thể thêm thông báo thành công vào session nếu muốn
        } catch (Exception e) {
            e.printStackTrace();
            // Có thể thêm thông báo lỗi vào session nếu muốn
        }
        resp.sendRedirect(req.getContextPath() + "/admin/categories"); // Chuyển hướng về trang danh sách
    }

    /**
     * Xử lý tìm kiếm category theo tên và hiển thị lại trang list.jsp với kết quả.
     */
    private void doGetSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Category> categories = categoryDao.findByName(keyword);
        req.setAttribute("categories", categories);
        req.setAttribute("keyword", keyword); // Gửi lại keyword để hiển thị trên ô tìm kiếm
        req.setAttribute("view", "/views/admin/category/list.jsp");
        req.getRequestDispatcher("/views/admin/layout.jsp").forward(req, resp);
    }

    /**
     * Lấy dữ liệu từ form, tạo một đối tượng Category mới và lưu vào database.
     */
    private void doPostCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Category category = new Category();
            // Sử dụng BeanUtils để tự động map các tham số từ request vào thuộc tính của object
            BeanUtils.populate(category, req.getParameterMap());
            categoryDao.create(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    /**
     * Lấy dữ liệu từ form, cập nhật thông tin cho Category đã có trong database.
     */
    private void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());
            categoryDao.update(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}