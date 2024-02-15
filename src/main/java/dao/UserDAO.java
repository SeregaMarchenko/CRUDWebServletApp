package dao;

import models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserById(int userId) throws SQLException;

    void createUser(User user) throws SQLException;

    boolean updateUserLogin(int userId, String newLogin) throws SQLException;

    boolean deleteUserById(int userId) throws SQLException;
}
