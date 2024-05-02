package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.DataRef;

import java.util.List;
import java.util.Map;

public interface DataService {

    Integer getTotalUsers();

    List<DataRef> loadAppliesTo();
    List<DataRef> loadUnitTypes();
    List<DataRef> loadDiscountCustomerEligibility();
    List<DataRef> loadDiscountMethods();
    List<DataRef> loadDiscountMinPurchases();
    List<DataRef> loadDiscountStatuses();
    List<DataRef> loadDiscountTypes();
    List<DataRef> loadDiscountValues();
    List<DataRef> loadProductStatuses();
    List<DataRef> loadSaleStatuses();
    List<DataRef> loadCountries();
}
