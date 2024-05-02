package com.ideracloud.salewell.domain;

import com.ideracloud.salewell.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "SW_DISCOUNT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity<Discount> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    DiscountTypes type;

    String code;
    String title;
    Double valuePercentage;
    Double fixedAmount;

    @Enumerated(EnumType.STRING)
    DiscountValue discountValue;

    @Enumerated(EnumType.STRING)
    DiscountAppliesTo appliesTo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SW_DISCOUNT_COLLECTION",
            joinColumns = {
                    @JoinColumn(name = "DISCOUNT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "COLLECTION_ID")})
    Set<Collection> collections;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SW_DISCOUNT_PRODUCT",
            joinColumns = {
                    @JoinColumn(name = "DISCOUNT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_ID") })
    Set<Product> products;

    @Enumerated(EnumType.STRING)
    DiscountMinPurchase minPurchase;

    Double minAmount;

    Integer minQtyItems;

    @Enumerated(EnumType.STRING)
    DiscountCustomerEligib customerEligib;

    Integer numberOfTimesUsage;

    boolean oneUsePerCustomer;

    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;

    Integer numberOfTimesUsed;

    @Enumerated(EnumType.STRING)
    DiscountStatus status;
}
