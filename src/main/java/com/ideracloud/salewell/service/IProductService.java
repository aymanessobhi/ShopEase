package com.ideracloud.salewell.service;


import com.ideracloud.salewell.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

    Pager<ProductDto> getPage(Pageable pageable);

    List<ProductDto> getAll();

    ProductDto get(Long id);

    ProductDto create(ProductDto request);
    ProductDto update(ProductDto dto);

    Pager<ProductDto> search(SearchRequest<ProductSearchDto> request);
    void upload(Long id, MultipartFile file);
    ProductImageDto updateImage(ProductImageDto dto);
    void deleteImage(Long id);

    Resource load(String filename);

    ProductDto findByBarcode(String barcode);
}
