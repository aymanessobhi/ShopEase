package com.ideracloud.salewell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SW_LOCATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    Country country;
    String adresse;
    String appartement;
    String codePostal;
    String ville;
    String telephone;

}
