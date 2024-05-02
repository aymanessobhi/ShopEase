package com.ideracloud.salewell.repository;

import com.ideracloud.salewell.domain.Category;
import com.ideracloud.salewell.domain.Product;
import com.ideracloud.salewell.domain.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends BaseRepository<Product> {

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findBySubCategory(SubCategory subCategory, Pageable pageable);

    Product findByBarcode(String barcode);
}
