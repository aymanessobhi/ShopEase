package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SW_CATEG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity<Category> {

	String code;
	String description;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
	@Column(nullable = true)
	@OrderBy("created_date ASC")
	@JsonManagedReference
	Set<CategoryImage> images;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	Set<SubCategory> subCategories;
}
