package com.screen.quickprint.common.pagination;


import com.screen.quickprint.common.model.PageResponse;
import com.screen.quickprint.common.pagination.model.PaginationRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationUtils {
    private PaginationUtils() {
    }

    public static Pageable createPageable(PaginationRequestDto paginationRequest) {
        return PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
                Sort.by(paginationRequest.getDir(), paginationRequest.getSortColumn()));
    }

    public static Pageable createPageable(int page, int size, Sort.Direction dir, String sortColumn) {
        return PageRequest.of(page, size, Sort.by(dir, sortColumn));
    }

    public static Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    public static <E, D> PageResponse<D> toPageResponse(Page<E> page, List<D> content) {
        return PageResponse.<D>builder()
                .content(content)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

}
