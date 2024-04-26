package com.ideracloud.salewell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SW_INVENTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity<BaseEntity> {
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "inventory")
	List<InventoryLine> lines;
	
	Date dateOfInventory;

}
