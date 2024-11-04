package com.ideracloud.salewell.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaleOrderDto extends BaseDto<SaleOrderDto>{

    Long id;
    List<SaleOrderLineDto> lines;

    Float total;

    Float payedAmount;

    String status;



}
