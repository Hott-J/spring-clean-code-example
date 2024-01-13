package com.test.example.domain.order;

import com.test.example.domain.user.User;
import java.util.List;

public interface OrderReader {

    List<Order> getAllByUser(User user);

    Order getOrder(Long orderId);
}
