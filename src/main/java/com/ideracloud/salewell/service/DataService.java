package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.DataRef;

import java.util.List;
import java.util.Map;

public interface DataService {

    Integer getTotalUsers();

    List<DataRef> loadAppliesTo();
}
