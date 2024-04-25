package com.ideracloud.salewell.service.impl;

import com.ideracloud.geomap.geomapapi.domain.Role;
import com.ideracloud.geomap.geomapapi.repository.RoleRepository;
import com.ideracloud.geomap.geomapapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        // Find role by name using the roleDao
        Role role = roleRepository.findByName(name);
        return role;
    }
}
