package configs;

public class StatementConfigs {
    public static final String SELECT_FROM_USERS_WHERE_ID = "SELECT * FROM users WHERE id = ?";
    public static final String INSERT_INTO_USERS_USERNAME_PASSWORD_VALUES = "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String UPDATE_USERS_SET_USERNAME_WHERE_ID = "UPDATE users SET username = ? WHERE id = ?";
    public static final String DELETE_FROM_USERS_WHERE_ID = "DELETE FROM users WHERE id = ?";
}
