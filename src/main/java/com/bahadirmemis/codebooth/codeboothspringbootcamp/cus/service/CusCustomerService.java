package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.converter.CusCustomerMapper;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.enums.CusErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.GenBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CusCustomerService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private final PasswordEncoder passwordEncoder;

    public List<CusCustomerDto> findAll() {

        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();

        List<CusCustomerDto> cusCustomerDtoList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }

    public CusCustomerDto findById(Long id) {

        Optional<CusCustomer> optionalCusCustomer = cusCustomerEntityService.findById(id);

        if (!optionalCusCustomer.isPresent()){
            return null;
        }

        CusCustomer cusCustomer = optionalCusCustomer.get();

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;

    }

    public CusCustomerDto findByIdWithControl(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findById(id).orElseThrow();

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        if (cusCustomerSaveRequestDto == null){
            throw new GenBusinessException(GenErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        String encodedPassword = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(encodedPassword);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        cusCustomerEntityService.delete(id);
    }

    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {

        boolean isExist = cusCustomerEntityService.isExist(cusCustomerUpdateRequestDto.getId());
        if (!isExist){
            throw new GenBusinessException(CusErrorMessage.CUSTOMER_DOES_NOT_EXIST);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);

        String encodedPassword = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(encodedPassword);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }
}
