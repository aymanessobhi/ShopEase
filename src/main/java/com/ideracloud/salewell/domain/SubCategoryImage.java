package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "SW_SUB_CATEG_IMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryImage extends BaseEntity<SubCategoryImage>{
    String filename;
    String path;
    boolean principle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    SubCategory subCategory;
}
