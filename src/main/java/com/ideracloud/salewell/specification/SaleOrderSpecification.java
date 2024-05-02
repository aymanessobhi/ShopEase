package com.ideracloud.salewell.specification;


import com.ideracloud.salewell.domain.SaleOrder;
import com.ideracloud.salewell.dto.SaleOrderSearchDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SaleOrderSpecification {

    public Specification<SaleOrder> findSales(SaleOrderSearchDto request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getDateDu() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), request.getDateDu()));
            if (request.getDateAu() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), request.getDateAu()));

            query.distinct(true);
            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



    Boolean isNotEmpty(String val) {
        return val != null && !val.isEmpty();
    }
}
