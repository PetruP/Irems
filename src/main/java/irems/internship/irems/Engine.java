package irems.internship.irems;

import java.util.Objects;

public class Engine {
    private static int COUNT = 0;
    private final int id;
    private final String model;
    private int price;
    private final String manufacturerName;
    private final int year;
    private final int noiseLevel;

    public Engine(String model, int price, String manufacturerName, int year, int noiseLevel) {
        this.id = COUNT++;
        this.model = model;
        this.price = price;
        this.manufacturerName = manufacturerName;
        this.year = year;
        this.noiseLevel = noiseLevel;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public int getYear() {
        return year;
    }

    public double getNoiseLevel() {
        return noiseLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return id == engine.id &&
                price == engine.price &&
                year == engine.year &&
                Double.compare(engine.noiseLevel, noiseLevel) == 0 &&
                Objects.equals(model, engine.model) &&
                Objects.equals(manufacturerName, engine.manufacturerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, price, manufacturerName, year, noiseLevel);
    }
}
