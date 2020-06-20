package irems.internship.irems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineReader {
    public List<Engine> readEngines() throws IOException {
        File myObj = new File("/Users/petru/Documents/engines.txt");
        Scanner myReader = new Scanner(myObj);
        List<Engine> engineList = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] parts = data.split("\\|");
            String engineType = parts[0];
            String model = parts[1];
            int price = Integer.parseInt(parts[2]);
            String manufacturerName = parts[3];
            int year = Integer.parseInt(parts[4]);
            int noiseLevel = Integer.parseInt(parts[5]);
            int additionalPrice = Integer.parseInt(parts[6]);
            Engine engine;
            if (engineType.equals("diesel")) {
                engine = new Diesel(model, price, manufacturerName, year, noiseLevel, additionalPrice);
            } else {
                engine = new Gas(model, price, manufacturerName, year, noiseLevel, additionalPrice);
            }

            engineList.add(engine);

        }
        myReader.close();
        return engineList;
    }
}
