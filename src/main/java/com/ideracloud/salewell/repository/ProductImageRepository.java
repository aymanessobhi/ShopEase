package com.ideracloud.salewell.repository;

import com.ideracloud.salewell.domain.ProductImage;

public interface ProductImageRepository extends BaseRepository<ProductImage> {

    @Override
    void deleteById(Long aLong);
}
