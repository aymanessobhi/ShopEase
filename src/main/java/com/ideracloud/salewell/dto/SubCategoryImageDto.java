package com.ideracloud.salewell.dto;

import lombok.Data;

@Data
public class SubCategoryImageDto extends BaseDto<SubCategoryImageDto>{

    Long id;
    SubCategoryDto subCategory;
}
