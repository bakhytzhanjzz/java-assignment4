import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Connection connection;

    public OrderRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coffeeShop", "postgres", "1234");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS orders (id SERIAL PRIMARY KEY, coffee_type VARCHAR(255), customization VARCHAR(255), username VARCHAR(255))";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(Order order, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (coffee_type, customization, username) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, order.getCoffee().getDescription());
            preparedStatement.setString(2, order.getCustomization());
            preparedStatement.setString(3, order.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders")) {

            while (resultSet.next()) {
                String coffeeType = resultSet.getString("coffee_type");
                String customization = resultSet.getString("customization");
                String username = resultSet.getString("username");
                Coffee coffee = createCoffee(coffeeType);
                orders.add(new Order(coffee, customization, username));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Coffee createCoffee(String coffeeType) {
        CoffeeFactory coffeeFactory = new SimpleCoffeeFactory();
        return coffeeFactory.createCoffee(coffeeType);
    }

    public void resetOrders() {
        try (Statement statement = connection.createStatement()) {
            String query = "DELETE FROM orders";
            statement.executeUpdate(query);
            System.out.println("Orders table reset successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
