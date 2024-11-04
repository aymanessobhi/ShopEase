package com.ideracloud.salewell.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "SW_CATEG_IMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryImage extends Base<CategoryImage> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String filename;
    String path;

    boolean principle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    Category category;
}
