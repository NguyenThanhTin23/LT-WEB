package JDBC.controllers;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	String fileName = req.getParameter("fname");
	File file = new File(Constant.DIR + "/" + fileName);
	resp.setContentType("image/jpeg");
	if (file.exists()) {
	IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
	}
	}
	}
}
