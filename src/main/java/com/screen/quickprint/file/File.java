package com.screen.quickprint.file;


import com.screen.quickprint.common.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity<Long> {
    private String fileName;
    private String fileUrl;   // S3 or MinIO URL
    private String fileType;
    private Long fileSize;
}