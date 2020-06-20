package irems.internship.irems;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EngineService {
    private final List<Engine> engines = new ArrayList<>();

    public EngineService() throws IOException {
        engines.addAll(new EngineReader().readEngines());
    }

    public Engine delete(final int id) {
        Engine vacationToDelete = getOrThrow(id);
        engines.remove(vacationToDelete);
        return vacationToDelete;
    }

    public Engine getOrThrow(int id) {
        return engines.stream()
                .filter(vacation -> vacation.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("could not find vacation with id " + id));
    }

    public Engine add(Engine engine, String type, int additionalPrice) {
        Engine newEngine;
        if (type.equals("diesel")) {
            newEngine = new Diesel(engine.getModel(), engine.getPrice(), engine.getManufacturerName(), engine.getYear(), engine.getYear(), additionalPrice);
        } else {
            newEngine = new Gas(engine.getModel(), engine.getPrice(), engine.getManufacturerName(), engine.getYear(), engine.getYear(), additionalPrice);
        }
        engines.add(newEngine);
        return newEngine;
    }

    public List<Engine> getAll() {
        return Collections.unmodifiableList(engines);
    }

    public Engine replace(int id, Engine engine, int additionalPrice) {
        Engine engineToReplace = getOrThrow(id);
        Engine newEngine;
        if (engineToReplace instanceof Diesel) {
            newEngine = new Diesel(engine.getModel(), engine.getPrice(), engine.getManufacturerName(), engine.getYear(), engine.getId(), additionalPrice);
        } else {
            newEngine = new Gas(engine.getModel(),engine.getPrice(), engine.getManufacturerName(), engine.getYear(), engine.getId(), additionalPrice);
        }
        engines.remove(engineToReplace);
        engines.add(id - 1, newEngine);
        return newEngine;
    }

    public List<Engine> getByType(String type) {
        List<Engine> results = new ArrayList<>();
        for (Engine e : engines) {
            if (type.equals("diesel")) {
                if (e instanceof Diesel) {
                    results.add(e);
                }
            } if (type.equals("gas")) {
                if (e instanceof Gas) {
                    results.add(e);
                }
            }
        }
        return results;
    }

    public int getTotalPrice() {
        int result = 0;
        for (Engine e : engines) {
            result += e.getPrice();
        }
        return result;
    }

    public void exportEngines() throws IOException {
        BufferedWriter gasWriter = new BufferedWriter(new FileWriter("exportedGasEngines.txt"));
        BufferedWriter dieselWriter = new BufferedWriter(new FileWriter("exportedDieselEngines.txt"));
        for (Engine e : engines) {
            if (e instanceof Diesel) {
                dieselWriter.write(e.getModel() + "|" + e.getPrice() + "|" + e.getManufacturerName() + "|" + e.getYear() + "|" + e.getNoiseLevel() + "|" + ((Diesel) e).getPollutionTax());
            }
            if (e instanceof Gas) {
                gasWriter.write(e.getModel() + "|" + e.getPrice() + "|" + e.getManufacturerName() + "|" + e.getYear() + "|" + e.getNoiseLevel() + "|" + ((Gas) e).getElectricEnginePrice());
            }
        }
        dieselWriter.close();
        gasWriter.close();
    }
}
