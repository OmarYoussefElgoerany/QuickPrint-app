package com.screen.quickprint.common.pagination.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequestDto {
    @Min(value = 0, message = "Page number cannot be negative")
    private int page = 0;

    @Min(value = 1, message = "Size must be at least 1")
    private int size = 10;
    
    private String sortColumn = "createdDate";

    private Sort.Direction dir = Sort.Direction.DESC;
}
