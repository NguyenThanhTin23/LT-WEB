package JDBC.util;

import org.apache.taglibs.standard.lang.jstl.Constants;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "MultiPartServlet", urlPatterns = {"/multiPartServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,     // 1MB
        maxFileSize = 1024 * 1024 * 5,       // 5MB
        maxRequestSize = 1024 * 1024 * 25    // 25MB
)
public class MultipartServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return Constants.DEFAULT_FILENAME;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uploadPath = Constants.UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                if (!fileName.equals(Constants.DEFAULT_FILENAME)) {
                    part.write(uploadPath + File.separator + fileName);
                }
            }
            request.setAttribute("message", "File " + fileName + " uploaded successfully!");
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "Error: " + fne.getMessage());
        }

        getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);
    }
}
