package com.ideracloud.salewell.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductSearchDto implements SearchDto<ProductSearchDto> {

    String codeCategory;
    String codeSubCategory;
}
