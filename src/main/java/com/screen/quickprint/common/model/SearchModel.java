package com.screen.quickprint.common.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SearchModel {
    private int page = 1;
    private int pageSize = 10;
    private String qry = "";
    private String sortColumn = "createdDate";
    private Sort.Direction dir = Sort.Direction.DESC;
}
