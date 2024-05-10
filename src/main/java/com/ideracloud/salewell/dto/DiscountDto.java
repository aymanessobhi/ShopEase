package com.ideracloud.salewell.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ideracloud.salewell.enums.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class DiscountDto extends BaseDto<DiscountDto>{
    Long id;
    DiscountTypes discountType;
    DiscountMethod discountMethod;
    String discountCode;
    String autoCode;
    DiscountValue discountValue;
    Double percentage;
    Double amount;
    DiscountAppliesTo specification;
    String searchCollections;
    String searchProducts;
    boolean oncePerOrder;
    DiscountMinPurchase minimumPurchaseRequirement;
    Double minimumAmountValue;
    Integer minimumQuantityValue;
    DiscountCustomerEligib customerEligibility;
    String specificSegmentsInput;
    String specificCustomersInput;
    String totalUsageLimit;
    boolean limitPerCustomer;
    boolean combineWithProductDiscounts;
    boolean combineWithOrderDiscounts;
    boolean combineWithShippingDiscounts;
    //Set<CollectionDto> collection;
    Set<ProductDto> product;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    Date startDate;
    String startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    Date endDate;
    String endTime;
    DiscountStatus status;
}
