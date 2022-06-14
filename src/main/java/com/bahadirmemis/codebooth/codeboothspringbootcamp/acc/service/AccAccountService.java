package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccAccountMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccAccountMapperAbstract;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.EnumGenStatus;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccAccountMapperAbstract accAccountMapperAbstract;

    public List<AccAccountDto> findAll() {

        List<AccAccount> accAccountList = accAccountEntityService.findAll();

        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);

        return accAccountDtoList;
    }

    public AccAccountDto findById(Long id) {

        AccAccount accAccount = accAccountEntityService.findById(id).orElseThrow();

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public AccAccountDto save(AccAccountSaveRequestDto accAccountSaveRequestDto) {

        String ibanNo = getIbanNo();

        AccAccount accAccount = accAccountMapperAbstract.convertToAccAccount(accAccountSaveRequestDto);
        accAccount.setIbanNo(ibanNo);
        accAccount.setStatus(EnumGenStatus.ACTIVE);
        accAccount.setCurrentBalance(BigDecimal.ZERO);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    private String getIbanNo() {

        String randomNumberAsString = StringUtil.getRandomNumberAsString(24);

        return "TR" + randomNumberAsString;
    }

    public void cancel(Long id) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(id);

        accAccount.setStatus(EnumGenStatus.PASSIVE);
        accAccount.setCancelDate(new Date());
        accAccountEntityService.save(accAccount);
    }
}
