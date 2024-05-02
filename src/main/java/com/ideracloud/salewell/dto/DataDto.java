package com.ideracloud.salewell.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DataDto {

    List<DataRef> appliesTo;
    List<DataRef> customerEligib;
    List<DataRef> discountMethods;
    List<DataRef> discMinP;
    List<DataRef> discStatus;
    List<DataRef> discTypes;
    List<DataRef> discountValues;
    List<DataRef> prodStatus;
    List<DataRef> saleStatus;
    List<DataRef> units;
    List<DataRef> countries;

}

