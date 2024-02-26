public class Espresso implements Coffee{
    @Override
    public String getDescription(){
        return "Espresso";
    }

    @Override
    public double getCost(){
        return 2.0;
    }

    @Override
    public String customize() {
        return "Customization not available for Espresso";
    }
}
