package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccAccountMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccAccountMapperAbstract;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccMoneyTransferMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.*;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccMoneyTransfer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccActivityType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountActivityEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccMoneyTransferEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.util.AccAccountUtil;
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
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;
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

        String ibanNo = AccAccountUtil.getIbanNo();

        AccAccount accAccount = accAccountMapperAbstract.convertToAccAccount(accAccountSaveRequestDto);
        accAccount.setIbanNo(ibanNo);
        accAccount.setStatus(EnumGenStatus.ACTIVE);
        accAccount.setCurrentBalance(BigDecimal.ZERO);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public void cancel(Long id) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(id);

        accAccount.setStatus(EnumGenStatus.PASSIVE);
        accAccount.setCancelDate(new Date());
        accAccountEntityService.save(accAccount);
    }

    public void transferMoney(AccMoneyTransferRequestDto accMoneyTransferRequestDto) {

        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferRequestDto);
        accMoneyTransfer.setTransactionDate(new Date());

        AccAccount accAccountFrom = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdFrom());
        AccAccount accAccountTo = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdTo());

        validateCurrencyTypes(accAccountFrom, accAccountTo);

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

        moneyOut(accMoneyActivityDtoFrom);
        moneyIn(accMoneyActivityDtoTo);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);
    }

    private void moneyIn(AccMoneyActivityDto accMoneyActivityDto) {

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateNewBalanceForMoneyIn(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        createAccountActivity(accMoneyActivityDto.getAccAccount().getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);
    }

    private void moneyOut(AccMoneyActivityDto accMoneyActivityDto) {

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateAndControlNewBalanceForMoneyOut(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        createAccountActivity(accAccount.getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);
    }

    private void validateCurrencyTypes(AccAccount accAccountFrom, AccAccount accAccountTo) {
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new RuntimeException("Currency types must be same!");
        }
    }

    private void updateCurrentBalance(AccAccount accAccountFrom, BigDecimal newBalance) {
        accAccountFrom.setCurrentBalance(newBalance);
        accAccountFrom = accAccountEntityService.save(accAccountFrom);
    }

    private void createAccountActivity(Long accAccountId, BigDecimal amount, BigDecimal newBalance, EnumAccActivityType activityType) {
        AccAccountActivity accAccountActivity = new AccAccountActivity();
        accAccountActivity.setActivityType(activityType);
        accAccountActivity.setAmount(amount);
        accAccountActivity.setAccAccountId(accAccountId);
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity.setTransactionDate(new Date());
        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);
    }

    private BigDecimal calculateAndControlNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = calculateNewBalanceForMoneyOut(amount, currentBalance);
        validateBalance(newBalance);
        return newBalance;
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Insufficient Balance");
        }
    }

    private BigDecimal calculateNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.subtract(amount);
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyIn(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.add(amount);
        return newBalance;
    }

    public void withdraw(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.WITHDRAW)
                .build();

        moneyOut(accMoneyActivityDtoTo);

    }

    public void deposit(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.DEPOSIT)
                .build();

        moneyIn(accMoneyActivityDtoTo);
    }
}
