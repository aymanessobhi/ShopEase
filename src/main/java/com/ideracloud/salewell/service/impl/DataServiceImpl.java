package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.dto.DataRef;
import com.ideracloud.salewell.enums.*;
import com.ideracloud.salewell.repository.CountryRepository;
import com.ideracloud.salewell.repository.UserRepository;
import com.ideracloud.salewell.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Integer getTotalUsers() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public List<DataRef> loadAppliesTo() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountAppliesTo.COLLECTIONS.name(),DiscountAppliesTo.COLLECTIONS.getLabel()));
        list.add(new DataRef(DiscountAppliesTo.PRODUCTS.name(), DiscountAppliesTo.PRODUCTS.getLabel()));
        return list;
    }
    @Override
    public List<DataRef> loadUnitTypes() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(Unit.KILOGRAM.name(), Unit.KILOGRAM.getDescription()));
        list.add(new DataRef(Unit.UNITE.name(), Unit.UNITE.getDescription()));
        list.add(new DataRef(Unit.LITRE.name(), Unit.LITRE.getDescription()));
        list.add(new DataRef(Unit.GRAMME.name(), Unit.GRAMME.getDescription()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountCustomerEligibility() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountCustomerEligib.ALL.name(), DiscountCustomerEligib.ALL.getLabel()));
        list.add(new DataRef(DiscountCustomerEligib.SPECIFIC.name(), DiscountCustomerEligib.SPECIFIC.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountMethods() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountMethod.AUTOMATIC.name(), DiscountMethod.AUTOMATIC.getLabel()));
        list.add(new DataRef(DiscountMethod.CODE.name(), DiscountMethod.CODE.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountMinPurchases() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountMinPurchase.NO_MIN.name(), DiscountMinPurchase.NO_MIN.getLabel()));
        list.add(new DataRef(DiscountMinPurchase.MIN_AMOUNT.name(), DiscountMinPurchase.MIN_AMOUNT.getLabel()));
        list.add(new DataRef(DiscountMinPurchase.MIN_QTE.name(), DiscountMinPurchase.MIN_QTE.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountStatuses() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountStatus.ACTIVE.name(), DiscountStatus.ACTIVE.getLabel()));
        list.add(new DataRef(DiscountStatus.EXPIRED.name(), DiscountStatus.EXPIRED.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountTypes() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountTypes.OFF_PRODUCT.name(), DiscountTypes.OFF_PRODUCT.getLabel()));
        list.add(new DataRef(DiscountTypes.BUYGET.name(), DiscountTypes.BUYGET.getLabel()));
        list.add(new DataRef(DiscountTypes.AMOUNT_OFF.name(), DiscountTypes.AMOUNT_OFF.getLabel()));
        list.add(new DataRef(DiscountTypes.FREE_SHIP.name(), DiscountTypes.FREE_SHIP.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadDiscountValues() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(DiscountValue.FIXED.name(), DiscountValue.FIXED.getLabel()));
        list.add(new DataRef(DiscountValue.PERCENT.name(), DiscountValue.PERCENT.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadProductStatuses() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(ProductStatus.NEW.name(), ProductStatus.NEW.getLabel()));
        list.add(new DataRef(ProductStatus.ACTIVE.name(), ProductStatus.ACTIVE.getLabel()));
        list.add(new DataRef(ProductStatus.INACTIF.name(), ProductStatus.INACTIF.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadSaleStatuses() {
        List<DataRef> list = new ArrayList<>();
        list.add(new DataRef(SaleStatus.DRAFT.name(), SaleStatus.DRAFT.getLabel()));
        list.add(new DataRef(SaleStatus.PAYE.name(), SaleStatus.PAYE.getLabel()));
        list.add(new DataRef(SaleStatus.PAYE_PARTIEL.name(), SaleStatus.PAYE_PARTIEL.getLabel()));
        return list;
    }

    @Override
    public List<DataRef> loadCountries() {
        List<DataRef> countries = new ArrayList<>();
        List<Country> countryList = countryRepository.findAll();
        for (Country country : countryList) {
            countries.add(new DataRef(country.getCode(), country.getDescription()));
        }
        return countries;
    }
}
