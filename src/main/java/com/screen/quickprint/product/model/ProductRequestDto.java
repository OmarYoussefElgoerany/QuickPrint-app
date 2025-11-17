package com.screen.quickprint.product.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name must be at most 100 characters")
    private String name;
    private String description;
    @NotBlank(message = "size is required")
    private String size;
    private String colorType;
    private Double basePrice;
    private Integer pageCounts;
    private Boolean isActive = true;
}
