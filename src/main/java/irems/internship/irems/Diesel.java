package irems.internship.irems;

public class Diesel extends Engine {
    private int pollutionTax;

    public int getPollutionTax() {
        return pollutionTax;
    }

    public Diesel(String model, int price, String manufacturerName, int year, int noiseLevel, int pollutionTax) {
        super(model, price, manufacturerName, year, noiseLevel);
        this.pollutionTax = pollutionTax;
        this.setPrice(price + pollutionTax);
    }
}
