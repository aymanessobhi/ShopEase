package com.ideracloud.salewell.dto;

import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.domain.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data

public class StaffDto extends BaseDto<StaffDto>{
    Long id;
    String name;
    String access;
    String posRole;
    Set<LocationDto> locations;

}
