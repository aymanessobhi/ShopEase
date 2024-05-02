package com.ideracloud.salewell.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto extends BaseDto<CategoryDto> {

	Long id;
	String code;
	String description;
	List<CategoryImageDto> images;

	List<SubCategoryDto> subCategories;
}
