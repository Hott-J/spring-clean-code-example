package com.test.example.infrastructure.order;

import com.test.example.domain.order.Order;
import com.test.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    Optional<Order> findByOrderId(Long orderId);
}
