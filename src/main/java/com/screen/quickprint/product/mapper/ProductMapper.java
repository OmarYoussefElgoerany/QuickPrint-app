package com.screen.quickprint.product.mapper;

import com.screen.quickprint.product.data.entity.Product;
import com.screen.quickprint.product.model.ProductRequestDto;
import com.screen.quickprint.product.model.ProductResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Request DTO → Entity
    Product toEntity(ProductRequestDto dto);

    // Entity → Response DTO
    ProductResponseDto toResponseDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchUpdate(ProductRequestDto dto, @MappingTarget Product product);
}
