package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ideracloud.salewell.enums.ProductStatus;
import com.ideracloud.salewell.enums.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "SW_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity<Product> {
	
	@ManyToOne(fetch = FetchType.EAGER)
	Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	SubCategory subCategory;
	
	String title;
	
	String description;

	String barcode;
	
	BigDecimal buyingPrice;

	BigDecimal salePrice;

	BigDecimal comparePrice;
	
	Integer availableQty;

	String sku;

	@Enumerated(EnumType.STRING)
	Unit unitOfMeasure;

	@Enumerated(EnumType.STRING)
	ProductStatus status;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, orphanRemoval = true)
	@Column(nullable = true)
	@OrderBy("created_date ASC")
	@JsonManagedReference
	Set<ProductImage> images;
	
	
	

}
