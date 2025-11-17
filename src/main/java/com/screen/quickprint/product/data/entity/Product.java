package com.screen.quickprint.product.data.entity;

import com.screen.quickprint.common.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity<Long> {
    private String name;
    private String description;
    private String size;
    private String colorType;
    private Double basePrice;
    private Integer pageCounts;
    private Boolean isActive = true;
}
