package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Product;
import com.ideracloud.salewell.domain.SaleOrder;
import com.ideracloud.salewell.domain.SaleOrderLine;
import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.dto.SaleOrderDto;
import com.ideracloud.salewell.dto.SaleOrderSearchDto;
import com.ideracloud.salewell.dto.SearchRequest;
import com.ideracloud.salewell.enums.SaleStatus;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.SaleOrderMapper;
import com.ideracloud.salewell.repository.SaleLineRepository;
import com.ideracloud.salewell.repository.SaleRepository;
import com.ideracloud.salewell.service.ISaleService;
import com.ideracloud.salewell.specification.SaleOrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {

    @Autowired
    SaleLineRepository solRepository;
    @Autowired
    SaleRepository repository;
    @Autowired
    SaleOrderMapper mapper;

    @Autowired
    SaleOrderSpecification soSpecification;

    @Override
    public Pager<SaleOrderDto> getPage(Pageable pageable) {
        Page entities = repository.findAll(pageable);
        return mapper.toDtosWithPagination(entities);
    }

    @Override
    public List<SaleOrderDto> getAll() {
        List<SaleOrder> entities = repository.findAll();
        return mapper.toDtos(entities);
    }

    @Override
    public SaleOrderDto get(Long id) {
        SaleOrder obj = repository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Data not found for given parameters {} :" + id));
        return mapper.toDto(obj);
    }

    @Override
    public Pager<SaleOrderDto> search(SearchRequest<SaleOrderSearchDto> request) {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Page<SaleOrderDto> list = repository.findAll(soSpecification.findSales(request.getCriteria()), pageRequest);
        return this.mapper.toDtosWithPagination(list);
    }

    @Override
    public SaleOrderDto update(SaleOrderDto dto) {
        //Update lines
        dto.getLines().forEach(line -> {
            solRepository.findById(line.getId()).map(entity -> {
                if(entity.getQte().doubleValue() != line.getQte()){
                    entity.setQte(new BigDecimal(line.getQte()));
                    entity.setAmount(new BigDecimal(line.getAmount()));
                    entity.setLastModifiedDate(new Date());
                    entity.setLastModifiedBy(dto.getLastModifiedBy());
                    return solRepository.save(entity);
                }else{
                    return null;
                }
            });
        });

        //Update sale order
        return repository.findById(dto.getId()).map(saleOrder -> {
            saleOrder.setTotal(new BigDecimal(dto.getTotal()));
            saleOrder.setPayedAmount(new BigDecimal(dto.getPayedAmount()));
            saleOrder.setLastModifiedDate(new Date());
            saleOrder.setLastModifiedBy(dto.getLastModifiedBy());
            return mapper.toDto(repository.save(saleOrder));
        }).orElseGet(null);
    }

    public void deleteLine(Long orderId, Long lineId){
        SaleOrder so = repository.findById(orderId).orElse(null);
        if(so != null && so.getStatus().equals(SaleStatus.DRAFT)){
            solRepository.deleteById(lineId);
        }
    }

    public void deleteOrder(Long orderId){
        SaleOrder so = repository.findById(orderId).orElse(null);
        if(so != null && so.getStatus().equals(SaleStatus.DRAFT)){
            repository.deleteById(orderId);
        }
    }
    @Override
    public SaleOrderDto create(SaleOrderDto dto) {
        SaleOrder so = new SaleOrder();
        so.setTotal(new BigDecimal(dto.getTotal()));
        so.setStatus(SaleStatus.DRAFT);
        so.setPayedAmount(new BigDecimal(dto.getPayedAmount()));
        so.setCreatedBy(dto.getCreatedBy());
        repository.save(so);
        so.setLines(dto.getLines().stream().map(line -> {
            SaleOrderLine sol = new SaleOrderLine();
            Product p = new Product();
            p.setId(line.getProduct().getId());
            sol.setProduct(p);
            sol.setQte(new BigDecimal(line.getQte()));
            sol.setSale(so);
            sol.setAmount(new BigDecimal(line.getAmount()));
            solRepository.save(sol);
            return sol;
        }).collect(Collectors.toList()));
        return mapper.toDto(so);
    }


}
