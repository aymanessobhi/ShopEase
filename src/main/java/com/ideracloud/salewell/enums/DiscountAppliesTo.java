package com.ideracloud.salewell.enums;

public enum DiscountAppliesTo {

    COLLECTIONS("Collections"), PRODUCTS("Products");

    String label;

    DiscountAppliesTo(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
