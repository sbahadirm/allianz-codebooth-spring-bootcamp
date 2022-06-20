package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class NonTransactionalConstantService {

    private final CusCustomerEntityService cusCustomerEntityService;

    private Map<Long, CusCustomer> idAndCustomerMap = new LinkedHashMap<>();

    public CusCustomer findById(Long id){

        CusCustomer cusCustomer = idAndCustomerMap.get(id);

        if (cusCustomer != null){
            return cusCustomer;
        }

        cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        idAndCustomerMap.put(id, cusCustomer);

        return cusCustomer;
    }

    @Transactional(propagation = Propagation.NEVER)
    public void saveNever() {

        CusCustomer customer = TransactionUtil.getCustomer("ts20");
        cusCustomerEntityService.save(customer);

        System.out.println("end");
    }
}
