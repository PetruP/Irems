package irems.internship.irems;

public class Gas extends Engine {
    private int electricEnginePrice;

    public Gas(String model, int price, String manufacturerName, int year, int noiseLevel, int electricEnginePrice) {
        super(model, price, manufacturerName, year, noiseLevel);
        this.electricEnginePrice = electricEnginePrice;
        this.setPrice(price + electricEnginePrice);
    }

    public int getElectricEnginePrice() {
        return electricEnginePrice;
    }
}
