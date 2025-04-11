package qwack;

import qwack.models.LoginModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static qwack.models.UsersModel.follow;

/**
 * @author Benji Bettagi, Kevin McLaughlin
 */
@WebServlet(name = "Qwack", urlPatterns = {"/Qwack"})
public class Qwack extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String email_register = request.getParameter("email_register");
        String password_register = request.getParameter("password_register");
        ArrayList<Post> postList = Database.postList();
        String test = (LoginModel.login(email, password));
        System.out.println("*************************");
        System.out.println(test);
        if (action != null) {
            if (email != "" && password != "" && action.equals("login")) {
                if (LoginModel.login(email, password) != "false") {
                    session = request.getSession();
                    session.setAttribute("user", User.pullUser(email));
                    String url = "/Home";
                    postList = Database.postList(User.pullUser(email));
                    request.setAttribute("user", User.pullUser(email));
                    request.setAttribute("postList", postList);
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else if (LoginModel.login(email, password).equals("false")) {
                    JOptionPane.showMessageDialog(null, "BAD INFO");
                    String url = "/login.jsp";
                    request.setAttribute("postList", postList);
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {
                    String url = "/login.jsp";
                    request.setAttribute("postList", postList);
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }
//            if (session.getAttributeNames() != null) {
//                String url = "/index.jsp";
//                ArrayList<Post> postList = Database.postList();
//                request.setAttribute("postList", postList);
//                request.setAttribute("user", User.pullUser(session.getAttribute("email").toString()));
//                getServletContext().getRequestDispatcher(url).forward(request, response);
//            }
            } else if (name != "" && email_register != "" && action.equals("add")) {
                if (name.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Names cannot contain a space");
                    request.setAttribute("postList", postList);
                    String url = "/login.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {
                    LoginModel.register(name, email_register, password_register);
                    User user = new User(email_register, name, password_register, "views/assets/default.png", 0);
                    try {
                        follow(user.getName(), user.getName());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    session.setAttribute("user", user);
                    request.setAttribute("postList", postList);
                    String url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill out all fields!");
                String url = "/login.jsp";
                request.setAttribute("postList", postList);
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        } else {
            session.invalidate();
            String url = "/login.jsp";
            request.setAttribute("postList", postList);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }
    private static final Logger LOG = Logger.getLogger(Qwack.class.getName());

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
