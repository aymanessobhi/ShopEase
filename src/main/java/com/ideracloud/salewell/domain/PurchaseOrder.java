package com.ideracloud.salewell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SW_PO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder extends BaseEntity<BaseEntity> {
	
	@ManyToOne
	Supplier supplier;
	
	Date orderDate;
	
	@OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
	List<PurchaseOrderLine> lines;

		

}
