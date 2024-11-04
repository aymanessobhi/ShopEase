package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Role;
import com.ideracloud.salewell.domain.User;
import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.dto.SearchRequest;
import com.ideracloud.salewell.dto.UserDto;
import com.ideracloud.salewell.dto.UserSearchDto;
import com.ideracloud.salewell.repository.UserRepository;
import com.ideracloud.salewell.service.RoleService;
import com.ideracloud.salewell.service.UserService;
import com.ideracloud.salewell.mapper.UserMapper;
import com.ideracloud.salewell.specification.UserCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepository userRepository; // Inject your user repository here

    @Autowired
    RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCriteria criteria;

    @Override
    public List<UserDto> listUsers() {
        return userRepository.findAll().stream().map(p -> userMapper.toDto(p)).collect(Collectors.toList());
    }

    @Override
    public Integer countUsers() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public UserDto createUser(UserDto dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        userRepository.findById(dto.getId()).map(u ->{
            u.setNom(dto.getNom());
            u.setPrenom(dto.getPrenom());
            u.setEmail(dto.getEmail());
            u.setUsername(dto.getUsername());
            u.setTel(dto.getTel());
            return userRepository.save(u);
        }).orElseGet(() -> null);

        return userMapper.toDto(userRepository.findById(dto.getId()).orElse(null));
    }

    @Override
    public UserDto findUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }


    @Override
    public Pager<UserDto> searchUser(SearchRequest<UserSearchDto> query) {
        int pageSize = Math.max(query.getSize(), 1);
        PageRequest pageRequest = PageRequest.of(query.getPage(), pageSize);
        log.info("Executing searchUser method with query: {}", query);

        try {
            Page<User> list = userRepository.findAll(criteria.fetchUsers(query.getCriteria()), pageRequest);
            return userMapper.toDtosWithPagination(list);
        } catch (Exception e) {
            log.error("Error executing searchUser method", e);
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    // Save user
    @Override
    public UserDto save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        // Set default role as USER
        Role role = roleService.findByName("ROLE_AGENT");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        // If email domain is admin.edu, add ADMIN role
        if(nUser.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("ROLE_ADMIN");
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userMapper.toDto(userRepository.save(nUser));
    }


    // Get user authorities
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
}
