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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload2.core.AbstractRequestContext;

import JDBC.model.Category;
import JDBC.service.CategoryService;
import JDBC.service.impl.CategoryServiceImpl;
import JDBC.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Category category = cateService.get(Integer.parseInt(id));
            req.setAttribute("category", category);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/editcategory.jsp");
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
            final String DIR = "D:\\upload";
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

         // Cách thay thế parseRequest(req):
         List<FileItem> items = upload.parseRequest(new ServletRequestContext(req));

            for (FileItem item : items) {
                switch (item.getFieldName()) {
                    case "id":
                        category.setId(Integer.parseInt(item.getString()));
                        break;
                    case "name":
                        category.setName(item.getString("UTF-8"));
                        break;
                    case "icon":
                        if (item.getSize() > 0) { // Có upload file mới
                            String originalFileName = item.getName();
                            int index = originalFileName.lastIndexOf(".");
                            String ext = originalFileName.substring(index + 1);
                            String fileName = System.currentTimeMillis() + "." + ext;
                            File file = new File(Constant.DIR + File.separator + "category" + File.separator + fileName);
                            item.write(file);
                            category.setIcon("category/" + fileName);
                        } else {
                            category.setIcon(null); // giữ null, service sẽ xử lý giữ lại icon cũ
                        }
                        break;
                }
            }

            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
