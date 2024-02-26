public class Cappuccino implements Coffee {
    @Override
    public String getDescription(){
        return "Cappuccino";
    }

    @Override
    public double getCost(){
        return 4.0;
    }

    @Override
    public String customize(){
        return "Customize your Cappuccino with cocoa powder";
    }
}
