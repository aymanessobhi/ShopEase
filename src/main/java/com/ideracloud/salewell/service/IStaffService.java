package com.ideracloud.salewell.service;
import com.ideracloud.salewell.dto.StaffDto;

import java.util.List;

public interface IStaffService {
    List<StaffDto> getAll();
    StaffDto get(Long id);
    StaffDto create(StaffDto request);
    StaffDto update(StaffDto dto);

}
