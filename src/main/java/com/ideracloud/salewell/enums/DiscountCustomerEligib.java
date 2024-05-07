package com.ideracloud.salewell.enums;

public enum DiscountCustomerEligib {

    ALL("All customers"), SPECIFIC_CUSTOMERS("Specific customers"), SPECIFIC_SEGMENTS("Specific segments");

    String label;

    DiscountCustomerEligib(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
