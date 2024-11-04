package com.ideracloud.salewell.controller;

import com.ideracloud.salewell.domain.User;
import com.ideracloud.salewell.dto.*;
import com.ideracloud.salewell.repository.UserRepository;
import com.ideracloud.salewell.security.TokenProvider;
import com.ideracloud.salewell.service.UserService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.*;

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
	public ApiResponse<UserDto> getUserById(@PathVariable Long id) {
		return ApiResponse.ok(userService.findUserById(id));
	}


	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken( request.getUsername(), request.getPassword() )
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtUtil.generateToken(authentication);
			final String userName = request.getUsername();
			User usr = userRepository.findByUsername(userName).orElse(null);
			AuthResponse response = new AuthResponse(userName,token, usr.getEmail());
			return ResponseEntity.ok(response);
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
