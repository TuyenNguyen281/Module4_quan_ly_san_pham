package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll( pageable );
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne( id );
    }

    @Override
    public void save(Product product) {
        productRepository.save( product );
    }

    @Override
    public void remove(Long id) {
        productRepository.delete( id );
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining( name, pageable );
    }
}
