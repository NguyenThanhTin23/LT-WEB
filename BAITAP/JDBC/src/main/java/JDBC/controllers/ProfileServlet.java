package JDBC.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import JDBC.model.Category;
import JDBC.service.CategoryService;
import JDBC.service.impl.CategoryServiceImpl;
import JDBC.util.Constant;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class ProfileServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() {
		userDAO = new UserDAO(); // khởi tạo DAO
	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		for (String token : contentDisp.split(";")) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return Constants.DEFAULT_FILENAME;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// giả sử user đã login, id lưu trong session
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		User user = userDAO.findById(userId);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");

		// xử lý ảnh upload
		String uploadPath = Constants.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		Part filePart = request.getPart("image");
		String fileName = getFileName(filePart);
		if (!fileName.equals(Constants.DEFAULT_FILENAME)) {
			filePart.write(uploadPath + File.separator + fileName);
		}

		// cập nhật user
		User user = new User(userId, fullname, phone, fileName);
		userDAO.update(user);

		response.sendRedirect(request.getContextPath() + "/profile");
	}

}
