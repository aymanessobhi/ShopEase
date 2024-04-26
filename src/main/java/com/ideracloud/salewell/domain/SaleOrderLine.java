package com.ideracloud.salewell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "SW_SO_LINE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderLine extends BaseEntity<SaleOrderLine> {
	
	@OneToOne
	Product product;
	
	BigDecimal qte;

	@ManyToOne
	SaleOrder sale;

	BigDecimal amount;

}
