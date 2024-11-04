package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Category;
import com.ideracloud.salewell.domain.SubCategory;
import com.ideracloud.salewell.domain.SubCategoryImage;
import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.dto.SubCategoryDto;
import com.ideracloud.salewell.dto.SubCategoryImageDto;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.SubCategoryMapper;
import com.ideracloud.salewell.repository.CategoryRepository;
import com.ideracloud.salewell.repository.SubCategoryImageRepository;
import com.ideracloud.salewell.repository.SubCategoryRepository;
import com.ideracloud.salewell.service.ISubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class SubCategoryService implements ISubCategoryService {


	protected final Path root = Paths.get(System.getProperty("user.home"),"uploads");

	@Autowired
	SubCategoryImageRepository sciRepository;

	@Autowired
	SubCategoryRepository repository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SubCategoryMapper mapper;

	@Override
	public Pager<SubCategoryDto> getPage(Pageable pageable) {
		Page entities = this.repository.findAll(pageable);
		return mapper.toDtosWithPagination(entities);
	}

	@Override
	public List<SubCategoryDto> getAll() {
		List<SubCategory> entities = repository.findAll();
		return mapper.toDtos(entities);
	}

	@Override
	public List<SubCategoryDto> getByCategory(Long id) {
		List<SubCategory> entities = repository.findByCategoryId(id);
		return mapper.toDtos(entities);
	}

	@Override
	public SubCategoryDto get(Long id) {
		SubCategory obj = repository.findById(id).orElseThrow(() ->
				new DataNotFoundException("Data not found for given parameters {} :" + id));
		return this.mapper.toDto(obj);
	}

	@Override
	public SubCategoryDto create(SubCategoryDto dto) {
		Category dbCateg = categoryRepository.findById(dto.getCategory().getId()).orElse(null);
		SubCategory created = mapper.toEntity(dto);
		created.setCategory(dbCateg);
		return mapper.toDto(repository.save(created));
	}

	@Override
	public SubCategoryDto update(SubCategoryDto dto) {
		Category dbCateg = categoryRepository.findById(dto.getCategory().getId()).orElse(null);
		return repository.findById(dto.getId())
				.map(entity -> {
					entity.setCategory(dbCateg);
					entity.setCode(dto.getCode());
					entity.setDescription(dto.getDescription());
					return mapper.toDto(repository.save(entity));
				})
				.orElseGet(() -> dto);
	}

	@Override
	public void upload(Long id, MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(id+"_"+file.getOriginalFilename()));
			SubCategory subCategory = repository.findById(id).orElse(null);
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/subcategory/files/")
					.path(String.valueOf(id))
					.path("/"+id+"_"+file.getOriginalFilename())
					.toUriString();

			if(subCategory != null){
				SubCategoryImage image = new SubCategoryImage();
				image.setFilename(file.getOriginalFilename());
				image.setPath(fileDownloadUri);
				image.setSubCategory(subCategory);
				sciRepository.save(image);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public SubCategoryImageDto updateImage(SubCategoryImageDto dto) {
		ModelMapper mm = new ModelMapper();

		SubCategoryImage idf = mm.map(dto,SubCategoryImage.class);
		return sciRepository.findById(dto.getId())
				.map(entity -> {
					//entity.setPrinciple(idf.isPrinciple());
					return mm.map(sciRepository.save(entity), SubCategoryImageDto.class);
				})
				.orElseGet(() -> mm.map(idf, SubCategoryImageDto.class));
	}


	@Override
	public void deleteImage(Long id) {
		SubCategoryImage image = sciRepository.findById(id).orElse(null);
		Path file = root.resolve(image.getFilename());
		try {
			Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		sciRepository.delete(image);
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				log.error("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
