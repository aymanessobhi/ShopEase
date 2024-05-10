package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Discount;
import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.dto.DiscountDto;
import com.ideracloud.salewell.mapper.DiscountMapper;
import com.ideracloud.salewell.repository.DiscountRepository;
import com.ideracloud.salewell.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    DiscountMapper mapper;
    @Autowired
    DiscountRepository repository;
    @Override
    public DiscountDto create(DiscountDto dto) {
        Discount entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<Discount> entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    @Override
    public DiscountDto updateDiscount(DiscountDto dto) {
        Discount discount = repository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found for id: " + dto.getId()));
        discount.setAmount(dto.getAmount());
        discount.setDiscountCode(dto.getDiscountCode());
        discount.setDiscountCode(dto.getDiscountCode());
        discount.setAutoCode(dto.getAutoCode());
        discount.setDiscountValue(dto.getDiscountValue());
        discount.setPercentage(dto.getPercentage());
        discount.setAmount(dto.getAmount());
        discount.setSpecification(dto.getSpecification());
        discount.setSearchCollections(dto.getSearchCollections());
        discount.setSearchProducts(dto.getSearchProducts());
        discount.setOncePerOrder(dto.isOncePerOrder());
        discount.setMinimumPurchaseRequirement(dto.getMinimumPurchaseRequirement());
        discount.setMinimumAmountValue(dto.getMinimumAmountValue());
        discount.setMinimumQuantityValue(dto.getMinimumQuantityValue());
        discount.setCustomerEligibility(dto.getCustomerEligibility());
        discount.setSpecificSegmentsInput(dto.getSpecificSegmentsInput());
        discount.setSpecificCustomersInput(dto.getSpecificCustomersInput());
        discount.setTotalUsageLimit(dto.getTotalUsageLimit());
        discount.setLimitPerCustomer(dto.isLimitPerCustomer());
        discount.setCombineWithProductDiscounts(dto.isCombineWithProductDiscounts());
        discount.setCombineWithOrderDiscounts(dto.isCombineWithOrderDiscounts());
        discount.setCombineWithShippingDiscounts(dto.isCombineWithShippingDiscounts());
        discount.setStartDate(dto.getStartDate());
        discount.setStartTime(dto.getStartTime());
        discount.setEndDate(dto.getEndDate());
        discount.setEndTime(dto.getEndTime());
        discount.setStatus(dto.getStatus());

        Discount updatedDiscount = repository.save(discount);
        return mapper.toDto(updatedDiscount);
    }

    @Override
    public void deleteDiscount(Long id) {
        Discount discount = repository.findById(id).orElse(null);
        if(discount != null) {
            repository.delete(discount);
        }
    }

    @Override
    public DiscountDto findDiscountById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
}
