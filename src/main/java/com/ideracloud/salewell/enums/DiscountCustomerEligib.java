package com.ideracloud.salewell.enums;

public enum DiscountCustomerEligib {

    ALL("All customers"), SPECIFIC("Specific customers");

    String label;

    DiscountCustomerEligib(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
