import java.util.Locale;

public class SimpleCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee(String type) {
        switch (type.toLowerCase()) {
            case "espresso":
                return new Espresso();
            case "latte":
                return new Latte();
            case "cappuccino":
                return new Cappuccino();
            default:
                throw new IllegalArgumentException("Invalid coffee type");
        }
    }
}
