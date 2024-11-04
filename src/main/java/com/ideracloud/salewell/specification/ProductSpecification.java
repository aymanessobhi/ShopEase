package com.ideracloud.salewell.specification;

import com.ideracloud.salewell.domain.Product;
import com.ideracloud.salewell.dto.ProductSearchDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Component
public class ProductSpecification {

    public Specification<Product> findProducts(ProductSearchDto request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if(isNotEmpty(request.getCodeCategory())){
                predicates.add(criteriaBuilder.equal(root.get("category").get("code"), request.getCodeCategory()));
            }


            if(isNotEmpty(request.getCodeSubCategory())){
                predicates.add(criteriaBuilder.equal(root.get("subCategory").get("code"), request.getCodeCategory()));
            }

            query.distinct(true);
            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



    Boolean isNotEmpty(String val) {
        return val != null && !val.isEmpty();
    }
}
