package com.ideracloud.salewell.enums;

public enum DiscountAppliesTo {

    COLLECTIONS("Fixed amount"), PRODUCTS("Percentage");

    String label;

    DiscountAppliesTo(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
