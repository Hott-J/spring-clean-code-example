package com.test.example.infrastructure.order;

import com.test.example.domain.order.OrderStore;
import com.test.example.domain.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;


    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }
}
