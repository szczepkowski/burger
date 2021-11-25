package pl.goreit.burger.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.OrderLineView;
import pl.goreit.burger.domain.model.OrderLine;

@Component
public class OrderLineFromModelToViewConverter implements Converter<OrderLine, OrderLineView> {

    @Override
    public OrderLineView convert(OrderLine orderLine) {
        return new OrderLineView(orderLine.getProductName(), orderLine.getPrice(), orderLine.getAmount());
    }
}
