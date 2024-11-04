package com.ideracloud.salewell.enums;

public enum DiscountValue {

    FIXED("Fixed amount"), PERCENT("Percentage");

    String label;

    DiscountValue(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
