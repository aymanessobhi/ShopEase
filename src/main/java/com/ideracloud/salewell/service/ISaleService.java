package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.Pager;
import com.ideracloud.salewell.dto.SaleOrderDto;
import com.ideracloud.salewell.dto.SaleOrderSearchDto;
import com.ideracloud.salewell.dto.SearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISaleService {
    Pager<SaleOrderDto> getPage(Pageable pageable);

    List<SaleOrderDto> getAll();

    SaleOrderDto get(Long id);

    SaleOrderDto update(SaleOrderDto dto);
    void deleteLine(Long orderId, Long lineId);
    void deleteOrder(Long orderId);
    SaleOrderDto create(SaleOrderDto dto);

    Pager<SaleOrderDto> search(SearchRequest<SaleOrderSearchDto> request);


}
