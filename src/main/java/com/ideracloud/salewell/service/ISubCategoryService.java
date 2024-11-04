package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISubCategoryService {

    Pager<SubCategoryDto> getPage(Pageable pageable);

    List<SubCategoryDto> getAll();

    List<SubCategoryDto> getByCategory(Long id);

    SubCategoryDto get(Long id);

    SubCategoryDto create(SubCategoryDto request);
    SubCategoryDto update(SubCategoryDto dto);
    void upload(Long id, MultipartFile file);
    SubCategoryImageDto updateImage(SubCategoryImageDto dto);

    void deleteImage(Long id);

    Resource load(String filename);



}