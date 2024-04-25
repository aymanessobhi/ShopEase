package com.ideracloud.salewell.controller;


import com.ideracloud.geomap.geomapapi.domain.AuthToken;
import com.ideracloud.geomap.geomapapi.dto.*;
import com.ideracloud.geomap.geomapapi.exception.BadRequestException;
import com.ideracloud.geomap.geomapapi.exception.ResourceUtil;
import com.ideracloud.geomap.geomapapi.repository.UserRepository;
import com.ideracloud.geomap.geomapapi.security.TokenProvider;
import com.ideracloud.geomap.geomapapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Slf4j
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired
	TokenProvider jwtUtil;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/list")
	public ApiResponse<List<UserDto>> listUsers() {
		return ApiResponse.ok(userService.listUsers());
	}

	@GetMapping("/{id}")
	public ApiResponse<PlanteurDto> getUserById(@PathVariable Long id) {
		return ApiResponse.ok(userService.findUserById(id));
	}

	@PostMapping("/createU")
	public ApiResponse<PlanteurDto> createPlanteur(@RequestBody @Valid UserDto dto, BindingResult result) {
		try{
			return ApiResponse.ok(HttpStatus.CREATED, userService.createUser(dto));
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
		}
	}

	@PutMapping("/updateU")
	public ApiResponse<PlanteurDto> updateUser(@RequestBody @Valid UserDto dto) {
		return ApiResponse.ok(userService.updateUser(dto));
	}



	@PostMapping({"searchU"})
	public ApiResponse<Pager<PlantationDto>> searchUser(@RequestBody SearchRequest<UserSearchDto> dto) {
		return ApiResponse.ok(userService.searchUser(dto));
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getUsername(),
							request.getPassword()
					)
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtUtil.generateToken(authentication);
			return ResponseEntity.ok(new AuthToken(token));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
		return ResponseEntity.ok(userService.save(user));
	}
}
