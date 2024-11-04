package com.ideracloud.salewell.dto;

import com.ideracloud.salewell.domain.ProductImage;
import com.ideracloud.salewell.domain.SubCategory;
import com.ideracloud.salewell.enums.ProductStatus;
import com.ideracloud.salewell.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto<ProductDto> {
    Long id;
    String title;
    String description;
    Double price;
    Double comparePrice;
    boolean taxable;
    Double costPerItem;
    Double profit;
    Double margin;
    boolean trackQte;
    Double weight;
    Integer quantity;
    Unit unitOfMeasure;
    String barcode;
    String sku;
    boolean shipping;
    boolean skuBarcode;
    ProductStatus status;
    Set<ProductImageDto> images;
    CategoryDto category;
    SubCategoryDto subCategory;


}
