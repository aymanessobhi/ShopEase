package com.ideracloud.salewell.domain;
package com.ideracloud.salewell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    String location;


}
