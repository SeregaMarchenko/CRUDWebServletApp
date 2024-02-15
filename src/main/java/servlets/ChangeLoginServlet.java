package servlets;

import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String userId = req.getParameter("id");
        String newLogin = req.getParameter("login");
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            if(userDAO.updateUserLogin(Integer.parseInt(userId),newLogin)) {
                req.setAttribute("message", "update was successful");
            }else {
                req.setAttribute("message", "update wasn't successful");
            }
            getServletContext().getRequestDispatcher("/info-page.jsp").forward(req,resp);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
