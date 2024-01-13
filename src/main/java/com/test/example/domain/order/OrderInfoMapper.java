package com.test.example.domain.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * 변경에 용이하기 위해 mapper 생성
 * mapstruct는 modelMapper보다 성능상 이점을 가져간다 (mapstruct: 컴파일시에 매핑 코드 생성, modelMapper: 런타임시에 리플렉션 사용하여 매핑)
 * mapping logic을 serviceImpl에 두면 비지니스 로직에 집중하기 어렵다.
 */
@Mapper
public interface OrderInfoMapper {

    OrderInfoMapper INSTANCE = Mappers.getMapper(OrderInfoMapper.class);

    List<OrderInfo> of(List<Order> orders);
}