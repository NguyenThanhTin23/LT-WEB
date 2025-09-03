package JDBC.controllers;
@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet{
	CategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	String id = req.getParameter("id");
	cateService.delete(Integer.parseInt(id));
	resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}

}
