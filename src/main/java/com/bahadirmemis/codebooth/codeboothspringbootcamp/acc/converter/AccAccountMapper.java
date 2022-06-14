package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccAccountMapper {

    AccAccountMapper INSTANCE = Mappers.getMapper(AccAccountMapper.class);

    @Mapping(target = "cusCustomerId", source = "cusCustomer.id")
    AccAccountDto convertToAccAccountDto(AccAccount accAccount);

//    @Mapping(target = "cusCustomerId", source = "cusCustomer.id")
    List<AccAccountDto> convertToAccAccountDtoList(List<AccAccount> accAccountList);

    AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
