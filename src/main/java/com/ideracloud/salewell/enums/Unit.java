package com.ideracloud.salewell.enums;

public enum Unit {
	
	KILOGRAM("KG","Kilogramme"),
	UNITE("Unité","Unité"),
	LITRE("Litre","Litre"),
	GRAMME ("Gramme","Gramme");


	private String code;
	private String description;

	Unit(String code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
