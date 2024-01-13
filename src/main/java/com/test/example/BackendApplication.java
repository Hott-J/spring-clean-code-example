package com.test.example;

import com.test.example.domain.order.Order;
import com.test.example.domain.product.Product;
import com.test.example.domain.user.User;
import com.test.example.infrastructure.order.OrderRepository;
import com.test.example.infrastructure.product.ProductRepository;
import com.test.example.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        User user = userRepository.save(User.builder()
                .name("정학제")
                .email("jhj13062004@naver.com")
                .password("password")
                .build());
        Product product = productRepository.save(Product.builder()
                .name("상품")
                .price(100000)
                .build());
        orderRepository.save(Order.builder()
                .user(user)
                .product(product)
                .paymentPrice(100000)
                .build());
    }
}
