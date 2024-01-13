package com.test.example.domain.order;

import com.test.example.common.exception.BusinessException;
import com.test.example.common.response.ErrorCode;
import com.test.example.common.util.TokenGenerator;
import com.test.example.domain.product.Product;
import com.test.example.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private static final String ORD_PREFIX = "ord_";

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    private String orderToken;

    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name="product_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name="payment_price")
    private int paymentPrice;

    @Builder
    public Order(User user, Product product, int paymentPrice) {
        if (user == null) throw new BusinessException(ErrorCode.NOT_FOUND, "user", null);
        if (product == null) throw new BusinessException(ErrorCode.NOT_FOUND, "product", null);
        if (paymentPrice != product.getPrice()) throw new BusinessException(ErrorCode.INVALID_PARAMETER, "order.price",
                List.of(String.valueOf(product.getPrice()), String.valueOf(paymentPrice)));
        this.orderToken = TokenGenerator.randomCharacterWithPrefix(ORD_PREFIX);
        this.user = user;
        this.product = product;
        this.paymentPrice = paymentPrice;
    }
}
