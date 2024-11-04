package com.ideracloud.salewell.enums;

public enum DiscountMethod {

    AUTOMATIC("Automatic discount"), CODE("Discount code");

    String label;

    DiscountMethod(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
