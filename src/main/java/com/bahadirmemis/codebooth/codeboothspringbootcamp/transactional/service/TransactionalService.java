package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Transactional
@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private final NonTransactionalService nonTransactionalService;

    public void save() {

        CusCustomer customer = TransactionUtil.getCustomer("ts2");

        cusCustomerEntityService.save(customer);

        System.out.println("end");
    }

    public void saveT2N() {

        CusCustomer customer = TransactionUtil.getCustomer("ts3-1");
        cusCustomerEntityService.save(customer);

        nonTransactionalService.save();

        System.out.println("end");
    }

    public void saveT2T() {

        CusCustomer customer = TransactionUtil.getCustomer("ts5-1");
        cusCustomerEntityService.save(customer);

        save();

        System.out.println("end");
    }

    public void saveButError() {

        CusCustomer customer = TransactionUtil.getCustomer("ts6-1");
        cusCustomerEntityService.save(customer);

//        try {
            throwException();
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        System.out.println("End");
    }

    private void throwException() {
        throw new RuntimeException("Error");
    }
}
