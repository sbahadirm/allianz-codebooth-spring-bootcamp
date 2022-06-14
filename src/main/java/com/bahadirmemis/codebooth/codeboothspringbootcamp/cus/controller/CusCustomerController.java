package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.CusCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {

    private final CusCustomerService cusCustomerService;

    @GetMapping
    public List<CusCustomerDto> findAll(){
        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();
        return cusCustomerDtoList;
    }

    @GetMapping("/{id}")
    public CusCustomerDto findById(@PathVariable Long id){
        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);
        return cusCustomerDto;
    }

    @PostMapping
    public CusCustomerDto save(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){
        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);
        return cusCustomerDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cusCustomerService.delete(id);
    }

    @PutMapping
    public CusCustomerDto update(@RequestBody CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        return cusCustomerDto;
    }
}
