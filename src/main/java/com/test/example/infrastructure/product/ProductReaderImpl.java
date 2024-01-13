package com.test.example.infrastructure.product;

import com.test.example.common.exception.BusinessException;
import com.test.example.common.response.ErrorCode;
import com.test.example.domain.product.Product;
import com.test.example.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "product.byId", List.of(productId.toString())));
    }
}
