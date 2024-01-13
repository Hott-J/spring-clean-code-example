package com.test.example.domain.order;

import com.test.example.domain.order.OrderInfo.OrderInfoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-25T23:15:11+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OrderInfoMapperImpl implements OrderInfoMapper {

    @Override
    public List<OrderInfo> of(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderInfo> list = new ArrayList<OrderInfo>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderInfo( order ) );
        }

        return list;
    }

    protected OrderInfo orderToOrderInfo(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderInfoBuilder orderInfo = OrderInfo.builder();

        orderInfo.orderToken( order.getOrderToken() );
        orderInfo.paymentPrice( order.getPaymentPrice() );

        return orderInfo.build();
    }
}
