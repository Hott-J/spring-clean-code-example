package com.test.example.domain.order;

import com.test.example.common.exception.SelfValidating;
import com.test.example.domain.product.Product;
import com.test.example.domain.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * service layer의 input
 * interfaces layer의 input과 별도로 분리하자 (변경 용이)
 */
public class OrderCommand {

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class RegisterOrder extends SelfValidating<RegisterOrder> {
        @NotNull(message = "상품 번호는 필수입니다.")
        private Long productId;
        @NotNull(message = "유저 번호는 필수입니다.")
        private Long userId;
        @NotNull(message = "결제 금액은 필수입니다.")
        private Integer paymentPrice;

        @Builder
        public RegisterOrder(Long productId, Long userId, Integer paymentPrice) {
            this.productId = productId;
            this.userId = userId;
            this.paymentPrice = paymentPrice;
            validateSelf();
        }

        public Order toEntity(User user, Product product) {
            return Order.builder()
                    .user(user)
                    .product(product)
                    .paymentPrice(paymentPrice)
                    .build();
        }
    }
}
