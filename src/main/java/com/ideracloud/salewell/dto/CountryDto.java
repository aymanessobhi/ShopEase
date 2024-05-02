package com.ideracloud.salewell.dto;

import lombok.Data;

@Data
public class CountryDto extends BaseDto<CountryDto>{
    Long id;
    String code;
    String description;
}
