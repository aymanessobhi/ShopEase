package com.ideracloud.salewell.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaleOrderLineDto extends BaseDto{
    Long id;
    ProductDto product;
    Float qte;

    Float amount;
}
