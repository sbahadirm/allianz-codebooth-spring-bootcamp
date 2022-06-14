package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.converter.AccAccountActivityMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccMoneyActivityRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccActivityType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountActivityEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice.AccAccountEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.validator.AccValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccAccountActivityService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;
    private final AccValidator accValidator;
    private final AccAccountActivityMapper accAccountActivityMapper;

    public AccMoneyActivityDto withdraw(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.WITHDRAW)
                .build();

        AccAccountActivity accAccountActivity = moneyOut(accMoneyActivityDtoTo);

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityMapper.convertToAccMoneyActivityDto(accAccountActivity);

        return accMoneyActivityDto;
    }

    public AccMoneyActivityDto deposit(AccMoneyActivityRequestDto accMoneyActivityRequestDto) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.DEPOSIT)
                .build();

        AccAccountActivity accAccountActivity = moneyIn(accMoneyActivityDtoTo);

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityMapper.convertToAccMoneyActivityDto(accAccountActivity);

        return accMoneyActivityDto;
    }

    public AccAccountActivity moneyIn(AccMoneyActivityDto accMoneyActivityDto) {

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateNewBalanceForMoneyIn(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        AccAccountActivity accountActivity = createAccountActivity(accMoneyActivityDto.getAccAccount().getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);

        return accountActivity;
    }

    public AccAccountActivity moneyOut(AccMoneyActivityDto accMoneyActivityDto) {

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateAndControlNewBalanceForMoneyOut(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        AccAccountActivity accountActivity = createAccountActivity(accAccount.getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);

        return accountActivity;
    }

    private void updateCurrentBalance(AccAccount accAccountFrom, BigDecimal newBalance) {
        accAccountFrom.setCurrentBalance(newBalance);
        accAccountFrom = accAccountEntityService.save(accAccountFrom);
    }

    private AccAccountActivity createAccountActivity(Long accAccountId, BigDecimal amount, BigDecimal newBalance, EnumAccActivityType activityType) {
        AccAccountActivity accAccountActivity = new AccAccountActivity();
        accAccountActivity.setActivityType(activityType);
        accAccountActivity.setAmount(amount);
        accAccountActivity.setAccAccountId(accAccountId);
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity.setTransactionDate(new Date());
        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);

        return accAccountActivity;
    }

    private BigDecimal calculateAndControlNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = calculateNewBalanceForMoneyOut(amount, currentBalance);
        accValidator.validateBalance(newBalance);
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.subtract(amount);
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyIn(BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.add(amount);
        return newBalance;
    }
}
