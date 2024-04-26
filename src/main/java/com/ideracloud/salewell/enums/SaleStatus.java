package com.ideracloud.salewell.enums;

public enum SaleStatus {

    DRAFT("En cours"), PAYE("Payé"), PAYE_PARTIEL("Payement incomplet");

    String label;


    SaleStatus(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
