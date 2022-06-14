package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccMoneyTransferMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyTransferDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyTransferRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccMoneyTransfer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccActivityType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccMoneyTransferEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.validator.AccValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccMoneyTransferService {

    private final AccAccountActivityService accAccountActivityService;
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountEntityService accAccountEntityService;
    private final AccValidator accValidator;

    public AccMoneyTransferDto transferMoney(AccMoneyTransferRequestDto accMoneyTransferRequestDto) {

        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferRequestDto);
        accMoneyTransfer.setTransactionDate(new Date());

        AccAccount accAccountFrom = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdFrom());
        AccAccount accAccountTo = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdTo());

        accValidator.validateCurrencyTypes(accAccountFrom, accAccountTo);

        AccMoneyActivityDto accMoneyActivityDtoFrom = AccMoneyActivityDto.builder()
                .accAccount(accAccountFrom)
                .amount(accMoneyTransferRequestDto.getAmount())
                .activityType(EnumAccActivityType.SEND)
                .build();

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccountTo)
                .amount(accMoneyTransferRequestDto.getAmount())
                .activityType(EnumAccActivityType.GET)
                .build();

        accAccountActivityService.moneyOut(accMoneyActivityDtoFrom);
        accAccountActivityService.moneyIn(accMoneyActivityDtoTo);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);

        AccMoneyTransferDto accMoneyTransferDto = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransferDto(accMoneyTransfer);

        return accMoneyTransferDto;
    }

}
