package com.test.example.domain.order;

import java.util.List;

/**
 * 도메인 서비스만을 보고 도메인의 전체적인 흐름을 파악할 수 있어야한다
 */
public interface OrderService {

    List<OrderInfo> findOrders(String userToken);

    String registerOrder(OrderCommand.RegisterOrder command);
}
