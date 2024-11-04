package com.ideracloud.salewell.repository;

import com.ideracloud.salewell.domain.SubCategory;

import java.util.List;

public interface SubCategoryRepository extends BaseRepository<SubCategory> {

    List<SubCategory> findByCategoryId(Long id);
}
