package com.test.example.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Dto는 너무 광범위하므로, 서비스 레이어 아웃풋을 Info로 명명
 */
@Builder
@Getter
@AllArgsConstructor
public class OrderInfo {

    private String orderToken; // 주문 토큰 추가 (식별)
    private int paymentPrice;
}
