package com.ideracloud.salewell.enums;

public enum DiscountStatus {

    ACTIVE("Active"), EXPIRED("Expired");

    String label;


    DiscountStatus(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
