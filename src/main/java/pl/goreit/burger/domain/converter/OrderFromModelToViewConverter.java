package pl.goreit.burger.domain.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.OrderView;
import pl.goreit.burger.domain.model.Order;

import java.util.stream.Collectors;

@Component
public class OrderFromModelToViewConverter implements Converter<Order, OrderView> {

    private final OrderLineFromModelToViewConverter orderLineFromModelToViewConverter;

    @Lazy
    public OrderFromModelToViewConverter(OrderLineFromModelToViewConverter orderLineFromModelToViewConverter) {
        this.orderLineFromModelToViewConverter = orderLineFromModelToViewConverter;
    }

    @Override
    public OrderView convert(Order order) {
        return new OrderView(order.getOrderId(),
                order.getOrderLines().stream()
                        .map(this.orderLineFromModelToViewConverter::convert)
                        .collect(Collectors.toList()),
                order.getOrderCost(),
                order.isAtLocation(),
                order.getCreatedAt()
        );
    }
}
