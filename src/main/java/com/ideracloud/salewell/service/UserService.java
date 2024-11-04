package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.dto.SearchRequest;
import com.ideracloud.salewell.dto.UserDto;
import com.ideracloud.salewell.dto.UserSearchDto;

import java.util.List;


public interface UserService {
    List<UserDto> listUsers();
    UserDto createUser (UserDto dto);
    UserDto updateUser(UserDto dto);
    UserDto findUserById(Long id);
    Integer countUsers();
    Pager<UserDto> searchUser(SearchRequest<UserSearchDto> query);

    // Save user
    UserDto save(UserDto user);
}
