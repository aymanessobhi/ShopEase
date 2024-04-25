package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.repository.UserRepository;
import com.ideracloud.salewell.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Integer getTotalUsers() {
        return Math.toIntExact(userRepository.count());
    }

}
