package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.CategoryImageDto;
import com.ideracloud.salewell.dto.Pager;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {

    Pager<CategoryDto> getPage(Pageable pageable);
    List<CategoryDto> getAll();
    CategoryDto get(Long id);
    CategoryDto create(CategoryDto request);
    CategoryDto update(CategoryDto dto);
    void upload(Long id, MultipartFile file);
    CategoryImageDto updateImage(CategoryImageDto dto);
    void deleteImage(Long id);
    Resource load(String filename);



}