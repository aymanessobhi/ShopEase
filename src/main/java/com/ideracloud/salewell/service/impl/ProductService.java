package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Category;
import com.ideracloud.salewell.domain.Product;
import com.ideracloud.salewell.domain.ProductImage;
import com.ideracloud.salewell.domain.SubCategory;
import com.ideracloud.salewell.dto.*;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.ProductMapper;
import com.ideracloud.salewell.repository.CategoryRepository;
import com.ideracloud.salewell.repository.ProductImageRepository;
import com.ideracloud.salewell.repository.ProductRepository;
import com.ideracloud.salewell.repository.SubCategoryRepository;
import com.ideracloud.salewell.service.IProductService;
import com.ideracloud.salewell.specification.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {

    protected final Path root = Paths.get(System.getProperty("user.home"),"uploads");

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductSpecification prodSpecification;

    @Autowired
    ProductImageRepository piRepository;


    @Override
    public Pager<ProductDto> getPage(Pageable pageable) {
        Page entities = repository.findAll(pageable);
        return mapper.toDtosWithPagination(entities);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> entities = repository.findAll();
        return this.mapper.toDtos(entities);
    }

    @Override
    public ProductDto get(Long id) {
        Product obj = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Data not found for given parameters {} :" + id));
        return mapper.toDto(obj);
    }

    @Override
    public Pager<ProductDto> search(SearchRequest<ProductSearchDto> request) {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Page<ProductDto> list = repository.findAll(prodSpecification.findProducts(request.getCriteria()), pageRequest);
        return this.mapper.toDtosWithPagination(list);
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product entity = mapper.toEntity(dto);
        if(StringUtils.hasLength(entity.getBarcode())){
            Product byBarcode =  repository.findByBarcode(entity.getBarcode());
            if(byBarcode == null) {
                return mapper.toDto(repository.save(entity));
            }else{
                return null;
            }
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ProductDto update(ProductDto dto) {
        Category dbCateg = categoryRepository.findById(dto.getCategory().getId()).orElse(null);
        SubCategory dbSubcateg = subCategoryRepository.findById(dto.getSubCategory().getId()).orElse(null);

        return repository.findById(dto.getId())
                .map(entity -> {
                    entity.setTitle(dto.getTitle());
                    entity.setDescription(dto.getDescription());
                    entity.setBarcode(dto.getBarcode());
                    entity.setQuantity(dto.getQuantity());
                    entity.setCostPerItem(new BigDecimal(dto.getCostPerItem()));
                    entity.setPrice(dto.getPrice());
                    entity.setUnitOfMeasure(dto.getUnitOfMeasure());
                    entity.setStatus(dto.getStatus());

                    if(dbCateg != null){
                        entity.setCategory(dbCateg);
                    }
                    if(dbSubcateg != null){
                        entity.setSubCategory(dbSubcateg);
                    }
                    return mapper.toDto(repository.save(entity));
                })
                .orElseGet(() -> dto);
    }

    @Override
    public void upload(Long id, MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(id+"_"+file.getOriginalFilename()));
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/product/files/")
                    .path(String.valueOf(id))
                    .path("/"+id+"_"+file.getOriginalFilename())
                    .toUriString();

            Product prod = repository.findById(id).orElse(null);

            ProductImage image = new ProductImage();
            image.setFilename(id+"_"+file.getOriginalFilename());
            image.setPath(fileDownloadUri);
            image.setProduct(prod);
            piRepository.save(image);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public ProductImageDto updateImage(ProductImageDto dto) {
        ModelMapper mm = new ModelMapper();

        ProductImage idf = mm.map(dto,ProductImage.class);
        return piRepository.findById(dto.getId())
                .map(entity -> {
                    entity.setPrinciple(idf.isPrinciple());
                    return mm.map(piRepository.save(entity), ProductImageDto.class);
                })
                .orElseGet(() -> mm.map(idf, ProductImageDto.class));
    }

    @Override
    public void deleteImage(Long id) {
        ProductImage image = piRepository.findById(id).orElse(null);
        Path file = root.resolve(image.getFilename());
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        piRepository.delete(image);
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

    @Override
    public ProductDto findByBarcode(String barcode) {
        return mapper.toDto(repository.findByBarcode(barcode));
    }
}
