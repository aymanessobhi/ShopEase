package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Category;
import com.ideracloud.salewell.domain.CategoryImage;
import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.CategoryImageDto;
import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.CategoryMapper;
import com.ideracloud.salewell.repository.CategoryImageRepository;
import com.ideracloud.salewell.repository.CategoryRepository;
import com.ideracloud.salewell.service.ICategoryService;
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
public class CategoryService implements ICategoryService {

	protected final Path root = Paths.get(System.getProperty("user.home"),"uploads");

	@Autowired
	CategoryMapper mapper;
	@Autowired
	CategoryRepository repository;

	@Autowired
	CategoryImageRepository ciRepository;

	@Override
	public CategoryDto create(CategoryDto dto) {
		Category entity = mapper.toEntity(dto);
		return mapper.toDto(repository.save(entity));
	}

	@Override
	public CategoryDto update(CategoryDto dto) {
		return repository.findById(dto.getId())
			      .map(entity -> {
			    	  entity.setCode(dto.getCode());
			    	  entity.setDescription(dto.getDescription());
			        return mapper.toDto(repository.save(entity));
			      })
			      .orElseGet(() -> dto);
	}

	@Override
	public Pager<CategoryDto> getPage(Pageable pageable) {
		Page entities = this.repository.findAll(pageable);
		return mapper.toDtosWithPagination(entities);
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> entities = this.repository.findAll();
		return this.mapper.toDtos(entities);
	}

	@Override
	public CategoryDto get(Long id) {
		Category obj = repository.findById(id).orElseThrow(() -> {
			return new DataNotFoundException("Data not found for given parameters {} :" + id);
		});
		return this.mapper.toDto(obj);
	}

	@Override
	public void upload(Long id, MultipartFile file) {

		try {
			Files.copy(file.getInputStream(), this.root.resolve(id+"_"+file.getOriginalFilename()));
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/category/files/")
					.path(String.valueOf(id))
					.path("/"+id+"_"+file.getOriginalFilename())
					.toUriString();

			Category categ = repository.findById(id).orElse(null);

			CategoryImage image = new CategoryImage();
			image.setFilename(id+"_"+file.getOriginalFilename());
			image.setPath(fileDownloadUri);
			image.setCategory(categ);
			ciRepository.save(image);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public CategoryImageDto updateImage(CategoryImageDto dto) {
		ModelMapper mm = new ModelMapper();

		CategoryImage idf = mm.map(dto,CategoryImage.class);
		return ciRepository.findById(dto.getId())
				.map(entity -> {
					entity.setPrinciple(idf.isPrinciple());
					return mm.map(ciRepository.save(entity), CategoryImageDto.class);
				})
				.orElseGet(() -> mm.map(idf, CategoryImageDto.class));
	}

	@Override
	public void deleteImage(Long id) {
		CategoryImage image = ciRepository.findById(id).orElse(null);
		Path file = root.resolve(image.getFilename());
		try {
			Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ciRepository.delete(image);
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
