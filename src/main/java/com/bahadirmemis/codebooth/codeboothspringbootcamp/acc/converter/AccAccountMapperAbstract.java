package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccAccountMapperAbstract {

    @Autowired
    CusCustomerEntityService cusCustomerEntityService;

    @Mapping(
            target = "cusCustomer",
            expression = "java(cusCustomerEntityService.findByIdWithControl(accAccountSaveRequestDto.getCusCustomerId()))"
    )
    public abstract AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
