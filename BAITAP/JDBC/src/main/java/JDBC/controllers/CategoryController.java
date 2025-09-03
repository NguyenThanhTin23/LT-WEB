package JDBC.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import JDBC.model.*;
import java.io.IOException;
import java.util.List;

import JDBC.service.impl.*;
@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryController extends HttpServlet{
	CategoryServiceImpl cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
	List<Category> cateList = cateService.getAll();
	req.setAttribute("cateList"
	, cateList);
	RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/listcategory.jsp");
	dispatcher.forward(req, resp);
	}

}
