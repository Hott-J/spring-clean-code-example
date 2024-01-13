package com.test.example.infrastructure.order;

import com.test.example.domain.order.OrderReader;
import com.test.example.common.exception.BusinessException;
import com.test.example.common.response.ErrorCode;
import com.test.example.domain.order.Order;
import com.test.example.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "order.byId", List.of(orderId.toString())));
    }
}
