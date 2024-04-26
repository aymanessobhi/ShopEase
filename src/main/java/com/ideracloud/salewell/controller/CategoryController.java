package com.ideracloud.salewell.controller;

import ma.ideratech.stock.common.BadRequestException;
import ma.ideratech.stock.dto.CategoryDto;
import ma.ideratech.stock.dto.CategoryImageDto;
import ma.ideratech.stock.dto.Pager;
import ma.ideratech.stock.dto.response.ApiResponse;
import ma.ideratech.stock.service.impl.CategoryService;
import ma.ideratech.stock.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	CategoryService service;

	@GetMapping({"list"})
	public ApiResponse<Pager<CategoryDto>> getPage(Pageable pageable) {
		return ApiResponse.ok(service.getPage(pageable));
	}

	@GetMapping({"getAll"})
	public ApiResponse<List<CategoryDto>> getAll() {
		return ApiResponse.ok(service.getAll());
	}

	@GetMapping({"/{id}"})
	public ApiResponse<CategoryDto> getOne(@PathVariable Long id) {
		return ApiResponse.ok(service.get(id));
	}

	@PostMapping({"create"})
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse create(@RequestBody @Valid CategoryDto dto, BindingResult result) {
		if (result.hasErrors()) {
			throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
		} else {
			return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
		}
	}

	@PutMapping({"update"})
	public ApiResponse<CategoryDto> update(@RequestBody @Valid CategoryDto dto) {
		return ApiResponse.ok(service.update(dto));
	}

	@PutMapping("/image/update")
	public ResponseEntity<CategoryImageDto> updateImage(@RequestBody @Valid CategoryImageDto dto){
		return ResponseEntity.ok(service.updateImage(dto));
	}

	@DeleteMapping("/image/delete/{fileId}")
	public void deleteFile(@PathVariable Long fileId) {
		service.deleteImage(fileId);
	}
}
