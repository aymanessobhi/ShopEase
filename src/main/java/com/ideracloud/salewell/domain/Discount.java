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
public class Discount extends Base<Discount> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    DiscountTypes discountType;

    @Enumerated(EnumType.STRING)
    DiscountMethod discountMethod;

    String discountCode;
    String autoCode;

    @Enumerated(EnumType.STRING)
    DiscountValue discountValue;

    Double percentage;
    Double amount;
    @Enumerated(EnumType.STRING)
    DiscountAppliesTo specification;
    String searchCollections;
    String searchProducts;
    boolean oncePerOrder;
    @Enumerated(EnumType.STRING)
    DiscountMinPurchase minimumPurchaseRequirement;
    Double minimumAmountValue;
    Integer minimumQuantityValue;
    @Enumerated(EnumType.STRING)
    DiscountCustomerEligib customerEligibility;
    String specificSegmentsInput;
    String specificCustomersInput;
    String totalUsageLimit;
    boolean limitPerCustomer;
    boolean combineWithProductDiscounts;
    boolean combineWithOrderDiscounts;
    boolean combineWithShippingDiscounts;

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

    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;
    String startTime;
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;
    String endTime;
    @Enumerated(EnumType.STRING)
    DiscountStatus status;
}
