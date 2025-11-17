package com.screen.quickprint.product.service;

import com.screen.quickprint.product.model.ProductRequestDto;
import com.screen.quickprint.product.model.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    void add(ProductRequestDto productReq);

    void delete(Long prodId);

    ProductResponseDto getById(Long id);

    void patch(Long prodId, ProductRequestDto productReq);

    List<ProductResponseDto> search(String keyword);

    Page<ProductResponseDto> getAll(PageRequest page);

    void deactivate(Long prodId);

}
