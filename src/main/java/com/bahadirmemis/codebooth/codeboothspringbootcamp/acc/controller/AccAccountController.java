package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.AccAccountService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<AccAccountDto> findAll(){
        List<AccAccountDto> accAccountDtoList = accAccountService.findAll();

        return accAccountDtoList;
    }

    @GetMapping("/{id}")
    public AccAccountDto findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return accAccountDto;
    }

    @PostMapping
    public AccAccountDto save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return accAccountDto;
    }

    @PatchMapping("/cancel/{id}")
    public void cancel(@PathVariable Long id){

        accAccountService.cancel(id);
    }
}
