package com.ideracloud.salewell.dto;

import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.domain.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class LocationDto extends BaseDto<LocationDto>{

    Long id;
    String name;
    CountryDto  country;
    String address;
    String appartment;
    String postalCode;
    String city;
    String phone;
    boolean fulfillOnlineOrders;
    boolean posEnabled;
}
