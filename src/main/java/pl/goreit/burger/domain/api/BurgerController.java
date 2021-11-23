package pl.goreit.burger.domain.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.model.Burger;
import pl.goreit.burger.domain.service.BurgerService;

import java.util.List;

@RestController
@RequestMapping("/api/burger")
public class BurgerController {

    private final BurgerService burgerService;

    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping()
    @ApiOperation(value = "pobiera  burgery")
    public List<BurgerView> getAll() {
        return burgerService.getAll();
    }

    @PostMapping()
    @ApiOperation(value = "dodaje  burgera")
    public BurgerView create(BurgerView burgerView) {
        return burgerService.create(burgerView);
    }

    @PutMapping()
    @ApiOperation(value = "updateuje  burgera")
    public BurgerView update(BurgerView burgerView) {
        return burgerService.update(burgerView);
    }

    @DeleteMapping()
    @ApiOperation(value = "archiwzuje burgera")
    public boolean delete(String name) {
        return burgerService.delete(name);
    }
}
