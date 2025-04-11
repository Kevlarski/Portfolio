package qwack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import qwack.models.ProfileModel;

/**
 *
 * @author itsme
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
@MultipartConfig
public class Profile extends HttpServlet {

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
        long size = 0;
        String name = request.getParameter("name");
        User user = (User) session.getAttribute("user");
        int user_id = user.getId();
        String username = request.getParameter("username");
        ArrayList<Post> postList = new ArrayList();
        if (username !=null){
            System.out.println(username);
        }
        if (name != null || size > 0) {
            if (name.length() > 0 || request.getPart("file").getSize() > 0) {
                if (request.getPart("file").getSize() > 0 && name.length() > 0) {
                    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filePart.getInputStream();
                    String fileSavePath = getServletContext().getRealPath("views/assets/userData/");
                    Files.copy(fileContent, new File(fileSavePath, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    picPath = ("views/assets/userData/" + fileName);
                    ProfileModel.updateUser(name, user_id, picPath);
                    user.setName(name);
                    user.setPic(picPath);
                    Database.picUpdate(user_id, picPath);
                } else if (name.length() > 0) {
                    ProfileModel.updateUser(name, user_id);
                    user.setName(name);
                } else {
                    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filePart.getInputStream();
                    String fileSavePath = getServletContext().getRealPath("views/assets/userData/");
                    Files.copy(fileContent, new File(fileSavePath, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    picPath = ("views/assets/userData/" + fileName);
                    ProfileModel.updateUser(user_id, picPath);
                    Database.picUpdate(user_id, picPath);
                    user.setPic(picPath);
                }

            }
        }
        String url = "/profile.jsp";
        postList = Database.postList(user_id);
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
