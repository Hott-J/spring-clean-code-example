package com.test.example.interfaces.order;

import com.test.example.domain.order.OrderCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * OrderRequest의 속성명이 변경되면 maaper만 바꾸면 된다.
 * OrderRequest를 그대로 서비스 레이어의 파라미터로 사용한다면, api spec 변경이나 확장에 따라 서비스 레이어도 변경이 일어나게된다.
 * 이는 OCP를 위배한다.
 */
@Mapper
public interface OrderRequestMapper {

    OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "paymentPrice", source = "paymentPrice")
    OrderCommand.RegisterOrder of(OrderRequest.RegisterOrderRequest request);
}
