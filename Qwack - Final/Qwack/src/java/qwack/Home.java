package qwack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import qwack.models.PostModel;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
@MultipartConfig
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String picPath = null;
        String postContent = request.getParameter("content");
        User user = (User) session.getAttribute("user");
        Cookie userCookie = new Cookie("user", user.getName());
        System.out.println(user.getName());
        response.addCookie(userCookie);

        if (picPath != null || postContent != null) {
            if (request.getPart("file").getSize() != 0) {
                Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                String fileSavePath = getServletContext().getRealPath("views/assets/userData/");
                Files.copy(fileContent, new File(fileSavePath, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                picPath = ("views/assets/userData/" + fileName);
                if (picPath != null) {
                    PostModel.postQwack(postContent, user.getId(), picPath);
                }
            } else if (postContent.length() > 0) {
                PostModel.postQwack(postContent, user.getId());
            }
        }
        String parameterNames = "";
        int postID;
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.contains("L.x")) {
                String[] parameterName = parameterNames.split("L.x");
                postID = Integer.parseInt(parameterName[0]);
                Database.likePost(postID);
            } else if (parameterNames.contains("R.x")) {
                String[] parameterName = parameterNames.split("R.x");
                postID = Integer.parseInt(parameterName[0]);
                PostModel.reqwackInsert(postID, user.getId());
            }
        }

        String url = "/index.jsp";
        ArrayList<Post> postList = Database.postList(user);
        request.setAttribute("user", user);
        request.setAttribute("postList", postList);
        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
