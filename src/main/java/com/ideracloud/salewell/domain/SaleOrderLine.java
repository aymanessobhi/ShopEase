package com.ideracloud.salewell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "SW_SO_LINE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderLine extends Base<SaleOrderLine> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@OneToOne
	Product product;
	
	BigDecimal qte;

	@ManyToOne
	SaleOrder sale;

	BigDecimal amount;

}
