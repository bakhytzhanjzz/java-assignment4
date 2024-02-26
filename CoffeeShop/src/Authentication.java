import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private static final Map<String, User> users = new HashMap<>();

    static {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coffeeShop", "postgres", "1234");
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean signUp(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coffeeShop", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            System.out.println("Sign-up successful");
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean logIn(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coffeeShop", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
