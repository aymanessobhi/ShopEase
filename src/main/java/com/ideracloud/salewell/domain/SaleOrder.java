package com.ideracloud.salewell.domain;

import com.ideracloud.salewell.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity 
@Table(name = "SW_SO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrder extends BaseEntity<SaleOrder> {

	@OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	List<SaleOrderLine> lines;

	BigDecimal total;

	BigDecimal payedAmount;

	@Enumerated(EnumType.STRING)
	SaleStatus status;


	
}
