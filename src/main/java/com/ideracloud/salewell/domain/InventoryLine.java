package com.ideracloud.salewell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SW_INVENT_ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLine  extends BaseEntity<BaseEntity> {
	
	@OneToOne(fetch = FetchType.EAGER)
	Product product;
	
	BigDecimal qteStock;
	
	BigDecimal qteReel;
	
	@ManyToOne
	Inventory inventory;
	
	

}
