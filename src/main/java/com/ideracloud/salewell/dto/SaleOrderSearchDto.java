package com.ideracloud.salewell.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class SaleOrderSearchDto implements SearchDto<SaleOrderSearchDto> {

    Date dateDu;

    Date dateAu;
}
