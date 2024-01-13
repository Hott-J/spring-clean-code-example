package com.test.example.interfaces.order;

import com.test.example.domain.order.OrderCommand;
import com.test.example.domain.order.OrderCommand.RegisterOrder.RegisterOrderBuilder;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-25T20:30:31+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OrderRequestMapperImpl implements OrderRequestMapper {

    @Override
    public OrderCommand.RegisterOrder of(OrderRequest.RegisterOrderRequest request) {
        if ( request == null ) {
            return null;
        }

        RegisterOrderBuilder registerOrder = OrderCommand.RegisterOrder.builder();

        registerOrder.productId( request.getProductId() );
        registerOrder.userId( request.getUserId() );
        registerOrder.paymentPrice( request.getPaymentPrice() );

        return registerOrder.build();
    }
}
