package com.ideracloud.salewell.dto;

import lombok.Data;

@Data
public class CategoryImageDto extends BaseDto<CategoryImageDto>{

    Long id;
    CategoryDto category;
}
