package com.ideracloud.salewell.dto;
package com.ideracloud.salewell.dto;

import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.domain.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

public class StaffDto extends BaseDto<StaffDto>{
    Long id;
    String name;
    String access;
    String posRole;
    String location;


}
