package com.ideracloud.salewell.enums;

public enum ProductStatus {

    NEW("Nouveau"), ACTIVE("Active"), INACTIF("Inactif");

    String label;


    ProductStatus(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
