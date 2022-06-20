package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.*;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.AccAccountActivityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.AccAccountService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.AccMoneyTransferService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccAccountController {

    private final AccAccountService accAccountService;
    private final AccAccountActivityService accAccountActivityService;
    private final AccMoneyTransferService accMoneyTransferService;

    @GetMapping
    public ResponseEntity findAll(){
        List<AccAccountDto> accAccountDtoList = accAccountService.findAll();

        return ResponseEntity.ok(GenRestResponse.of(accAccountDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return ResponseEntity.ok(GenRestResponse.of(accAccountDto));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(accAccountDto));
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id){

        accAccountService.cancel(id);

        return ResponseEntity.ok(GenRestResponse.empty());
    }

    @PostMapping("/money-transfer")
    public ResponseEntity transferMoney(@RequestBody AccMoneyTransferRequestDto accMoneyTransferRequestDto){
        AccMoneyTransferDto accMoneyTransferDto = accMoneyTransferService.transferMoney(accMoneyTransferRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(accMoneyTransferDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.withdraw(accMoneyActivityRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(accMoneyActivityDto));
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.deposit(accMoneyActivityRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(accMoneyActivityDto));
    }

}
