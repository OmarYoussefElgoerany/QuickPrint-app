package com.screen.quickprint.common.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableResponseDto {
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;

}
