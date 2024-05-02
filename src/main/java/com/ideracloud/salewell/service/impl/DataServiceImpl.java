package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.dto.DataRef;
import com.ideracloud.salewell.enums.DiscountAppliesTo;
import com.ideracloud.salewell.repository.CountryRepository;
import com.ideracloud.salewell.repository.UserRepository;
import com.ideracloud.salewell.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Integer getTotalUsers() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public List<DataRef> loadAppliesTo() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountAppliesTo.COLLECTIONS.name(),DiscountAppliesTo.COLLECTIONS.getLabel()));
        list.add(new DataRef(DiscountAppliesTo.PRODUCTS.name(), DiscountAppliesTo.PRODUCTS.getLabel()));
        return list;
    }

}
