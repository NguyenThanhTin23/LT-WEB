package JDBC.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.FileUploadException;

import JDBC.model.Category;
import JDBC.service.CategoryService;
import JDBC.service.impl.CategoryServiceImpl;
import JDBC.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category category = new Category();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");

        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            // ⚡ Dùng ServletRequestContext để tránh lỗi HttpServletRequest missing type
            List<FileItem> items = servletFileUpload.parseRequest(new ServletRequestContext(req));

            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Trường input thường
                    if (item.getFieldName().equals("name")) {
                        category.setName(item.getString("UTF-8"));
                    }
                } else {
                    // Trường file upload
                    if (item.getFieldName().equals("icon") && item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;

                        File file = new File(Constant.DIR + "/category/" + fileName);
                        item.write(file);

                        category.setIcon("category/" + fileName);
                    }
                }
            }

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
