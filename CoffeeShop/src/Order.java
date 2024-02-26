public class Order {
    private Coffee coffee;
    private String customization;
    private String username;

    public Order(Coffee coffee, String customization, String username) {
        this.coffee = coffee;
        this.customization = customization;
        this.username = username;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public String getCustomization() {
        return customization;
    }

    public String getUsername() {
        return username;
    }
}
