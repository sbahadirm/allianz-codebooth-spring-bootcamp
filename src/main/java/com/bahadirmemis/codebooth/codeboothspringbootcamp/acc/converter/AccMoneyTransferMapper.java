package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyTransferRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccMoneyTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferMapper {

    AccMoneyTransferMapper INSTANCE = Mappers.getMapper(AccMoneyTransferMapper.class);

    AccMoneyTransfer convertToAccMoneyTransfer(AccMoneyTransferRequestDto accMoneyTransferRequestDto);
}
