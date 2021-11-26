package pl.goreit.burger.domain.api;

import org.springframework.web.bind.annotation.*;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.view.OrderView;
import pl.goreit.burger.domain.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderView createOrder(@RequestParam boolean atLocation) throws EntityNotFoundException {
        OrderView orderView = this.orderService.create("admin", atLocation);

        return orderView;
    }

    @GetMapping
    public List<OrderView> getAll() {
        return this.orderService.getAll("admin");
    }
}
