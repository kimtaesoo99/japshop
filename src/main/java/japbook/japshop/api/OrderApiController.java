package japbook.japshop.api;

import japbook.japshop.domain.Order;
import japbook.japshop.domain.OrderItem;
import japbook.japshop.repository.OrderRepository;
import japbook.japshop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
           orderItems.forEach(o -> o.getItem().getName());
        }
        return all;
    }

}
