package irems.internship.irems;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class EngineController {
    private final EngineService service;

    public EngineController(EngineService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Engine getById(@PathVariable int id) {
        return service.getOrThrow(id);
    }

    @GetMapping
    public List<Engine> getAll() {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    public Engine deleteEngine(@PathVariable int id) {
        return service.delete(id);
    }

    @PostMapping("{type}/{additionalPrice}")
    public Engine addVacation(@RequestBody Engine engine, @PathVariable String type, @PathVariable int additionalPrice) {
        return service.add(engine, type, additionalPrice);
    }

    @PutMapping("{id}/{additionalPrice}")
    public Engine replaceVacation(@PathVariable int id, @RequestBody Engine engine, @PathVariable int additionalPrice) {
        return service.replace(id, engine, additionalPrice);
    }

    @GetMapping("type/{type}")
    public List<Engine> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    @GetMapping("totalPrice")
    public int getTotalPrice() {
        return service.getTotalPrice();
    }

    @GetMapping("export")
    public void exportEngines() throws IOException {
        service.exportEngines();
    }
}
