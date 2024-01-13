package com.test.example.interfaces.order;

import com.test.example.common.response.CommonResponse;
import com.test.example.domain.order.OrderCommand;
import com.test.example.domain.order.OrderInfo;
import com.test.example.domain.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderServiceImpl orderServiceImpl;

    @GetMapping("users/{userToken}")
    public CommonResponse retrieveOrders(@PathVariable String userToken) {
        List<OrderInfo> response = orderServiceImpl.findOrders(userToken);
        return CommonResponse.success(response);
    }

    @PostMapping
    public CommonResponse registerOrder(@RequestBody OrderRequest.RegisterOrderRequest request) {
        OrderCommand.RegisterOrder command = OrderRequestMapper.INSTANCE.of(request);
        String response = orderServiceImpl.registerOrder(command);
        return CommonResponse.success(response);
    }
}
