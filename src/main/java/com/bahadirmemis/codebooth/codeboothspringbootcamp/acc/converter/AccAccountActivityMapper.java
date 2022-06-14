package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccAccountActivityMapper {

    @Autowired
    AccAccountEntityService accAccountEntityService;

    @Mapping(
            target = "accAccount",
            expression = "java(accAccountEntityService.findByIdWithControl(accAccountActivity.getAccAccountId()))"
    )
    public abstract AccMoneyActivityDto convertToAccMoneyActivityDto(AccAccountActivity accAccountActivity);
}
