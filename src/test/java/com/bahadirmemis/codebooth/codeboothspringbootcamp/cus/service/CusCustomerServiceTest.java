package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.enums.CusErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class CusCustomerServiceTest {

    @Mock
    private CusCustomerEntityService cusCustomerEntityService;

    @InjectMocks
    private CusCustomerService cusCustomerService;

    @Test
    void shouldFindAll() {
        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(0, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnCustomers() {

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        List<CusCustomer> cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(1, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnNull() {

        when(cusCustomerEntityService.findAll()).thenReturn(null);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertNull(cusCustomerDtoList);
    }

    @Test
    void shouldFindById() {

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.of(cusCustomer));

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(1L);

        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.empty());

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(1L);

        assertNull(cusCustomerDto);
    }

    @Test
    void shouldFindByIdWithControl() {

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.of(cusCustomer));

        CusCustomerDto cusCustomerDto = cusCustomerService.findByIdWithControl(1L);

        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotFindByIdWithControlWhenIdDoesNotExists() {

        when(cusCustomerEntityService.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> cusCustomerService.findByIdWithControl(1L));
    }

    @Test
    void shouldSave() {

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = mock(CusCustomerSaveRequestDto.class);
        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        assertEquals(1L, cusCustomerDto.getId());
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class,
                () -> cusCustomerService.save(null));

        assertEquals(GenErrorMessage.PARAMETER_CANNOT_BE_NULL, genBusinessException.getBaseErrorMessage());
    }

    @Test
    void shouldDelete() {

        doNothing().when(cusCustomerEntityService).delete(1L);

        cusCustomerService.delete(1L);

        verify(cusCustomerEntityService).delete(1L);
    }

    @Test
    void shouldUpdate() {

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = mock(CusCustomerUpdateRequestDto.class);

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(cusCustomerEntityService.isExist(anyLong())).thenReturn(Boolean.TRUE);
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        assertEquals(1L, cusCustomerDto.getId());

    }

    @Test
    void shouldNotUpdateWhenCustomerDoesNotExist() {

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = mock(CusCustomerUpdateRequestDto.class);

        when(cusCustomerEntityService.isExist(anyLong())).thenReturn(Boolean.FALSE);

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> cusCustomerService.update(cusCustomerUpdateRequestDto));

        assertEquals(CusErrorMessage.CUSTOMER_DOES_NOT_EXIST, genBusinessException.getBaseErrorMessage());

    }
}