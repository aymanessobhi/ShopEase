package com.ideracloud.salewell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "SW_STAFF")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Staff extends Base<Staff>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String access;
    String posRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SW_STAFF_LOCATION",
            joinColumns = {
                    @JoinColumn(name = "STAFF_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "LOCATION_ID") })
    Set<Location> locations;
}
