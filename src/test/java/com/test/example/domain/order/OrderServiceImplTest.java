package com.test.example.domain.order;

import com.test.example.common.exception.BusinessException;
import com.test.example.common.response.ErrorCode;
import com.test.example.domain.product.Product;
import com.test.example.domain.product.ProductReader;
import com.test.example.domain.user.User;
import com.test.example.domain.user.UserReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private ProductReader productReader;

    @Mock
    private UserReader userReader;

    @Mock
    private OrderStore orderStore;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 주문_저장할때_존재하는_회원과_상품_및_상품가격과_주문가격이_일치하면_주문에_성공한다() {
        // Given
        Long productId = 1L;
        Long userId = 1L;
        Integer paymentPrice = 1000;
        Integer productPrice = 1000;
        OrderCommand.RegisterOrder command = new OrderCommand.RegisterOrder(userId, productId, paymentPrice);

        Product product = Product.builder()
                .name("상품")
                .price(productPrice)
                .build();
        User user = User.builder()
                .name("정학제")
                .email("jhj13062004@naver.com")
                .password("1234")
                .build();
        Order order = Order.builder()
                .user(user)
                .product(product)
                .paymentPrice(paymentPrice)
                .build();

        when(productReader.getProduct(productId)).thenReturn(product);
        when(userReader.getUser(userId)).thenReturn(user);
        when(orderStore.store(any(Order.class))).thenReturn(order);

        // When
        String orderToken = orderServiceImpl.registerOrder(command);

        // Then
        verify(productReader).getProduct(productId);
        verify(userReader).getUser(userId);
        verify(orderStore).store(any(Order.class));
        assertNotNull(orderToken);
    }

    @Test
    void 주문을_저장할때_존재하지않는_상품이면_NOT_FOUND_예외가_발생한다() {
        // Given
        Long productId = 1L;
        Long userId = 1L;
        OrderCommand.RegisterOrder command = new OrderCommand.RegisterOrder(userId, productId, 1000);
        when(productReader.getProduct(productId)).thenThrow(new BusinessException(ErrorCode.NOT_FOUND, "product.byId", List.of(productId.toString())));

        // When & Then
        BusinessException thrownException = assertThrows(BusinessException.class, () -> {
            orderServiceImpl.registerOrder(command);
        });

        // 예외 세부 사항 검증
        assertEquals(ErrorCode.NOT_FOUND, thrownException.getErrorCode());
        assertTrue(thrownException.getPath().contains("product.byId"));

        verify(productReader).getProduct(productId);
        verifyNoInteractions(userReader);
        verifyNoInteractions(orderStore);
    }

    @Test
    void 주문을_저장할때_존재하지않는_유저이면_NOT_FOUND_예외가_발생한다() {
        // Given
        Long productId = 1L;
        Long userId = 1L;
        Integer paymentPrice = 1000;
        Integer productPrice = 2000;
        OrderCommand.RegisterOrder command = new OrderCommand.RegisterOrder(userId, productId, paymentPrice);
        when(userReader.getUser(userId)).thenThrow(new BusinessException(ErrorCode.NOT_FOUND, "user.byId", List.of(productId.toString())));

        // When & Then
        BusinessException thrownException = assertThrows(BusinessException.class, () -> {
            orderServiceImpl.registerOrder(command);
        });

        // 예외 세부 사항 검증
        assertEquals(ErrorCode.NOT_FOUND, thrownException.getErrorCode());
        assertTrue(thrownException.getPath().contains("user.byId"));

        verify(productReader).getProduct(productId);
        verify(userReader).getUser(userId);
        verifyNoInteractions(orderStore);
    }

    @Test
    void 주문을_저장할때_상품가격과_주문가격이_일치하지않으면_INVALID_PARAMETER_예외가_발생한다() {
        // Given
        Long productId = 1L;
        Long userId = 1L;
        Integer paymentPrice = 1000;
        Integer productPrice = 2000;
        OrderCommand.RegisterOrder command = new OrderCommand.RegisterOrder(userId, productId, paymentPrice);

        Product product = Product.builder()
                .name("상품")
                .price(productPrice)
                .build();
        User user = User.builder()
                .name("정학제")
                .email("jhj13062004@naver.com")
                .password("1234")
                .build();

        when(productReader.getProduct(productId)).thenReturn(product);
        when(userReader.getUser(userId)).thenReturn(user);

        // When & Then
        BusinessException thrownException = assertThrows(BusinessException.class, () -> {
            orderServiceImpl.registerOrder(command);
        });

        assertEquals(ErrorCode.INVALID_PARAMETER, thrownException.getErrorCode());
        assertTrue(thrownException.getPath().contains("order.price"));
    }
}