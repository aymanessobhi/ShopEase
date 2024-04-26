package com.ideracloud.salewell.enums;

public enum DiscountTypes {

    OFF_PRODUCT("Amount off products"), BUYGET("Buy X get Y"), AMOUNT_OFF("Amount off order"), FREE_SHIP("Free shipping");

    String label;


    DiscountTypes(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
