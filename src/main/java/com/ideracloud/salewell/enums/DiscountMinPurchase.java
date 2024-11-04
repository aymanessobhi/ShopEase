package com.ideracloud.salewell.enums;

public enum DiscountMinPurchase {

    NO_MIN("No minimum requirements"), MIN_AMOUNT("Minimum purchase amount"), MIN_QTE("Minimum quantity of items");

    String label;

    DiscountMinPurchase(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
