package dao;

import configs.DatabaseConnection;
import models.User;

import java.sql.*;
import java.util.Optional;

import static configs.StatementConfigs.*;

public class UserDAOImpl implements UserDAO {


    @Override
    public Optional<User> getUserById(int userId) throws SQLException {
        User user = null;
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_ID)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
                }
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void createUser(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_USERNAME_PASSWORD_VALUES)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean updateUserLogin(int userId, String newLogin) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_USERNAME_WHERE_ID)) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setInt(2, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteUserById(int userId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USERS_WHERE_ID)) {
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
