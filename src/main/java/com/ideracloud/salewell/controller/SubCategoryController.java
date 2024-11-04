package com.ideracloud.salewell.controller;

import com.ideracloud.salewell.exception.BadRequestException;
import com.ideracloud.salewell.dto.*;
import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.service.impl.SubCategoryService;
import com.ideracloud.salewell.utils.ResourceUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
@CrossOrigin("*")
public class SubCategoryController{

	@Autowired
	SubCategoryService service;


	@GetMapping({"getPage"})
	public ApiResponse<Pager<SubCategoryDto>> getPage(Pageable pageable) {
		return ApiResponse.ok(service.getPage(pageable));
	}

	@GetMapping({"getAll"})
	public ApiResponse<List<SubCategoryDto>> getAll() {
		return ApiResponse.ok(service.getAll());
	}

	@GetMapping({"category/{id}"})
	public ApiResponse<List<SubCategoryDto>> getAll(@PathVariable Long id) {
		return ApiResponse.ok(service.getByCategory(id));
	}

	@GetMapping({"{id}"})
	public ApiResponse<SubCategoryDto> getOne(@PathVariable Long id) {
		return ApiResponse.ok(service.get(id));
	}

	@PostMapping({"create"})
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<SubCategoryDto> create(@RequestBody @Valid SubCategoryDto dto, BindingResult result) {
		if (result.hasErrors()) {
			throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
		} else {
			return ApiResponse.ok(HttpStatus.OK, service.create(dto), "Object successfuly created");
		}
	}

	@PutMapping({"update"})
	public ApiResponse<SubCategoryDto> update(@RequestBody @Valid SubCategoryDto dto) {
		return ApiResponse.ok(service.update(dto));
	}

	@PostMapping("/upload")
	@ResponseBody
	public void uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("entityId") Long entityId) throws Exception {
		service.upload(entityId,file);
	}

	@PutMapping("image/update")
	public ResponseEntity<SubCategoryImageDto> updateImage(@RequestBody @Valid SubCategoryImageDto dto){
		return ResponseEntity.ok(service.updateImage(dto));
	}

	@DeleteMapping("image/delete/{fileId}")
	public void deleteFile(@PathVariable Long fileId) {
		service.deleteImage(fileId);
	}
}
