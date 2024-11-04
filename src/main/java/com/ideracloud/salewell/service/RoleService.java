package com.ideracloud.salewell.service;


import com.ideracloud.salewell.domain.Role;

public interface RoleService {
    // Method to find a Role by its name
    Role findByName(String name);
}
