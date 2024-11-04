package com.ideracloud.salewell.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubCategoryDto extends BaseDto<SubCategoryDto>{

    String code;
    String description;
    List<SubCategoryImageDto> images;
    CategoryDto category;
    Long id;
}
