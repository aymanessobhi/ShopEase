package com.ideracloud.salewell.repository;


import com.ideracloud.salewell.domain.CategoryImage;

public interface CategoryImageRepository extends BaseRepository<CategoryImage> {

    @Override
    void deleteById(Long aLong);
}
