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
public class Product extends Base<Product> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	SubCategory subCategory;
	
	String title;
	boolean taxable;
	String description;
	Double price;
	String barcode;
	
	BigDecimal costPerItem;

	Double profit;
	Double margin;
	boolean trackQte;
	Double weight;
	String sku;
	boolean shipping;
	boolean skuBarcode;
	Integer quantity;


	@Enumerated(EnumType.STRING)
	Unit unitOfMeasure;

	@Enumerated(EnumType.STRING)
	ProductStatus status;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, orphanRemoval = true)
	@Column(nullable = true)
	@OrderBy("createdDate ASC")
	@JsonManagedReference
	Set<ProductImage> images;

}
