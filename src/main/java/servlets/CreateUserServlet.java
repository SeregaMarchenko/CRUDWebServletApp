package servlets;

import dao.UserDAOImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.sql.SQLException;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            userDAO.createUser(new User(0, login, password));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
