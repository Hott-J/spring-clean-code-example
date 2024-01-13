package com.test.example.interfaces.order;

import lombok.*;

/**
 * static inner class 사용 이유
 * non-static inner class는 outer class를 참조하고 있음
 * outer class를 사용하지않더라도 gc 대상이 되지않으므로(외부 참조) 메모리 누수 발생
 */
public class OrderRequest {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest {
        private Long productId;

        private Long userId;

        private Integer paymentPrice;
    }
}
