import java.util.Scanner;
import java.util.List;

public class Customer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!Authentication.logIn(username, password)) {
            System.out.println("User does not exist. Let's sign up!");
            if (!Authentication.signUp(username, password)) {
                System.out.println("Exiting application. Please try again.");
                return;
            }
        }

        CoffeeFactory coffeeFactory = new SimpleCoffeeFactory();
        OrderRepository orderRepository = new OrderRepository();

        while (true) {
            displayMenu();

            int choice;
            do {
                System.out.print("Enter the number of your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice < 1 || choice > 5);

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    processCoffeeOrder(coffeeFactory, orderRepository, choice, scanner, username);
                    break;
                case 4:
                    System.out.println("Thank you for visiting! Have a great day!");
                    return;
                case 5:
                    resetOrders(orderRepository);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

            displayAllOrders(orderRepository);
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Coffee Menu ===");
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.println("3. Cappuccino");
        System.out.println("4. Exit");
        System.out.println("5. Reset Orders");
    }

    private static void processCoffeeOrder(CoffeeFactory coffeeFactory, OrderRepository orderRepository, int choice, Scanner scanner, String username) {
        String coffeeType = getCoffeeType(choice);
        Coffee coffee = coffeeFactory.createCoffee(coffeeType);
        System.out.println("\nOrdered: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());
        System.out.print("Do you want to customize your coffee? (yes/no): ");
        String customizeChoice = scanner.nextLine();
        String customization = "";
        if (customizeChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter customization: ");
            customization = scanner.nextLine();
            System.out.println(coffee.customize());
        }
        Order order = new Order(coffee, customization, username);
        orderRepository.addOrder(order, username);
    }

    private static void displayAllOrders(OrderRepository orderRepository) {
        List<Order> allOrders = orderRepository.getOrders();
        System.out.println("\n=== Total Orders ===");
        for (Order o : allOrders) {
            System.out.println("Coffee: " + o.getCoffee().getDescription() +
                    " | Customization: " + o.getCustomization());
        }
    }

    private static void resetOrders(OrderRepository orderRepository) {
        orderRepository.resetOrders();
        System.out.println("Orders reset successfully!");
    }

    private static String getCoffeeType(int choice) {
        switch (choice) {
            case 1:
                return "espresso";
            case 2:
                return "latte";
            case 3:
                return "cappuccino";
            default:
                return "";
        }
    }
}
