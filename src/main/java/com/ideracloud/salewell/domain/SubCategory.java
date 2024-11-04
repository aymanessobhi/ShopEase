package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SW_SUB_CATEG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory extends Base<SubCategory> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	Category category;

	String code;
	
	String description;

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER, orphanRemoval = true)
	@Column(nullable = true)
	@JsonManagedReference
	Set<SubCategoryImage> images;
	
}
