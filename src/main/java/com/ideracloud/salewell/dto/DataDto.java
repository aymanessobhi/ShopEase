package com.ideracloud.salewell.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class DataDto {

    Integer totalUsers;
    Integer totalPlanteurs;
    Integer totalPMales;
    Integer totalPFemales;
    Integer totalPlantations;
    Map<String, Long> plantationTypes;
}
