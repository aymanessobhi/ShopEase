package com.ideracloud.salewell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SW_SUPPLIER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends BaseEntity<BaseEntity> {
	
	String name;
	String telephone;
	String mobile;
	String email;

}
