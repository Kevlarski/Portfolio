package qwack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static qwack.models.UsersModel.follow;

/**
 * @author Kevin McLaughlin
 * @author Benji Bettagi
 */
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {

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
        User mainUser = (User) session.getAttribute("user");
        String email = mainUser.getEmail_address();
        String username = request.getParameter("username");
        String searchBar = request.getParameter("searchBar");
        String follow = "";
        ArrayList<Post> postList = new ArrayList();
        
        if ((searchBar != null) && (username == null)) {
            if (User.pullUserFromName(searchBar) != null) {
                username = searchBar;
            }
        }    
            
        //THIS MIGHT BE VOLATILE OR EXTREMELY STABLE, NO IDEA
        if ((!"noUser".equals(username))) {
            if (request.getParameter("follow") != null) {
                follow = request.getParameter("follow").substring(17);
            }
            System.out.println("username: " + username + ", follow: " + follow);
            session.setAttribute("viewingProfile", username);
            session.setAttribute("user", User.pullUser(email));
            String url = "/user.jsp";

            if (request.getParameter("follow") != null) {
                request.setAttribute("user", User.pullUserFromName(follow));
                postList = Database.postList(User.pullUserFromName(follow).getId());
                try {
                    follow(mainUser.getName(), follow);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else if (username != null) {
                request.setAttribute("user", User.pullUserFromName(username));
                postList = Database.postList(User.pullUserFromName(username).getId());
            }

            //Profile: User object of logged in use
            request.setAttribute("profile", User.pullUser(email));

            //postList: List of posts made by the profile being viewed
            request.setAttribute("postList", postList);

            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
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
