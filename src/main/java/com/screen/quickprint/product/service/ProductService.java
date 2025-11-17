package com.screen.quickprint.product.service;

import com.screen.quickprint.product.data.entity.Product;
import com.screen.quickprint.product.data.repository.ProductRepository;
import com.screen.quickprint.product.mapper.ProductMapper;
import com.screen.quickprint.product.model.ProductRequestDto;
import com.screen.quickprint.product.model.ProductResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepo;
    private ProductMapper mapper;

    @Override
    public void add(ProductRequestDto productReq) {
        Product product = mapper.toEntity(productReq);
        try {
            this.productRepo.save(product);
        } catch (DataIntegrityViolationException exp) {
            throw new DataIntegrityViolationException("Error while saving Product entity", exp);
        }
    }


    @Override
    public ProductResponseDto getById(Long prodId) {
        if (prodId == null) {
            throw new IllegalArgumentException("Product id is null");
        }
        Product product = productRepo.findById(prodId).orElseThrow(() -> {
            return new EntityNotFoundException("Product entity with ID " + prodId + " not found");
        });
        return mapper.toResponseDto(product);
    }

    @Override
    public void patch(Long prodId, ProductRequestDto productReq) {
        Product product = productRepo.findById(prodId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID: " + prodId + " not found"));

        mapper.patchUpdate(productReq, product);

        productRepo.save(product);
    }

    @Override
    public List<ProductResponseDto> search(String keyword) {
        return List.of();
    }

    @Override
    public Page<ProductResponseDto> getAll(PageRequest page) {
        return null;
    }

    @Override
    public void deactivate(Long prodId) {

    }

    @Override
    public void delete(Long prodId) {

    }

    private boolean exists(Long id) {
        return productRepo.existsById(id);
    }
}
