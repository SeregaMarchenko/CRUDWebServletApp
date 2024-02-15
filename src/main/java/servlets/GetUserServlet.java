package servlets;

import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/get")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            User user = userDAO.getUserById(Integer.parseInt(userId)).orElse(null);
            if (user == null) {
                req.setAttribute("message", "no user with this id");
                getServletContext().getRequestDispatcher("/info-page.jsp").forward(req, resp);
            } else {
                req.setAttribute("username", user.getLogin());
                req.setAttribute("password", user.getPassword());
                req.getRequestDispatcher("/page-info.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
