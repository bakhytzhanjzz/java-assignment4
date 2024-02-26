public class Latte implements Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double getCost() {
        return 3.5;
    }

    @Override
    public String customize() {
        return "Customize your Latte with flavoured syrup";
    }
}
