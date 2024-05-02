package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "SW_PRODUCT_IMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends Base<ProductImage> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String filename;
    String path;

    boolean principle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    Product product;
}
