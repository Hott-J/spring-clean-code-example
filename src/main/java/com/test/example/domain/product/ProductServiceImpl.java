package com.test.example.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductReader productReader;

    public Product getProduct(Long productId) {
        return productReader.getProduct(productId);
    }
}
