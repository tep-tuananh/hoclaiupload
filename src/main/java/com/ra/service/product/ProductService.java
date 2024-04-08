package com.ra.service.product;

import com.ra.models.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product save (Product product);
    Product findById(Long id);
    void delete(Long id);
}
