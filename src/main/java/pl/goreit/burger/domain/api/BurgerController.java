package pl.goreit.burger.domain.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.goreit.burger.domain.api.exceptions.DuplicateException;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
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

    @GetMapping("{name}")
    @ApiOperation(value = "pobiera  burgera po nazwie")
    public BurgerView getByName(@PathVariable String name) throws EntityNotFoundException {
        return burgerService.get(name);
    }

    @PostMapping()
    @ApiOperation(value = "dodaje  burgera")
    public BurgerView create(BurgerView burgerView) throws DuplicateException {
        return burgerService.create(burgerView);
    }

    @PutMapping()
    @ApiOperation(value = "updateuje  burgera")
    public BurgerView update(BurgerView burgerView) throws EntityNotFoundException {
        return burgerService.update(burgerView);
    }

    @DeleteMapping()
    @ApiOperation(value = "archiwzuje burgera")
    public boolean delete(String name) throws DuplicateException {
        return burgerService.delete(name);
    }
}
