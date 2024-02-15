package servlets;

import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("id");
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            if(userDAO.deleteUserById(Integer.parseInt(userId))) {
                req.setAttribute("message", "delete was successful");
            }else {
                req.setAttribute("message", "delete wasn't successful");
            }
            getServletContext().getRequestDispatcher("/info-page.jsp").forward(req,resp);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
