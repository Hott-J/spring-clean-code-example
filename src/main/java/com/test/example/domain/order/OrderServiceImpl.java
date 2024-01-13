package com.test.example.domain.order;

import com.test.example.domain.product.ProductReader;
import com.test.example.domain.product.Product;
import com.test.example.domain.user.User;
import com.test.example.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final ProductReader productReader;
    private final UserReader userReader;

    /**
     * 테스트가 어렵다.
     * User 객체를 생성하는게 아니라, DI를 통해 User를 주입하는 것이 좋다.
     * 단순히 DI만 하면 '폭탄돌리기'와 비슷하다. DIP 개념을 통해 추상화된 인터페이스를 주입하여,
     * 테스트 작성시 mock을 활용하여 주입할 수 있도록 testability를 높이자.
     * 주문 목록을 찾는 메소드에 User 객체를 생성하는 것은 어색하다.
     */
    @Transactional(readOnly = true)
    public List<OrderInfo> findOrders(String userToken) {
        User user = userReader.getUser(userToken);
        List<Order> orders = orderReader.getAllByUser(user);
        return OrderInfoMapper.INSTANCE.of(orders);
    }

    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder command) {
        Product product = productReader.getProduct(command.getProductId());
        User user = userReader.getUser(command.getUserId());
        Order order = command.toEntity(user, product);
        orderStore.store(order);
        return order.getOrderToken();
    }
}
