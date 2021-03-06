package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class NonTransactionalService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private TransactionalService transactionalService;
    private final TransactionalService2 transactionalService2;
    private final NonTransactionalConstantService nonTransactionalConstantService;

    @Autowired
    public void setTransactionalService(@Lazy TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    public void save() {

        CusCustomer customer = TransactionUtil.getCustomer("ts1");

        cusCustomerEntityService.save(customer);

        System.out.println("end");
    }

    public void saveN2T() {

        CusCustomer customer = TransactionUtil.getCustomer("ts4-1");
        cusCustomerEntityService.save(customer);

        transactionalService.save();

        System.out.println("end");
    }

    public void saveButError() {

        CusCustomer customer = TransactionUtil.getCustomer("ts7");
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

    public void saveN2M() {

        CusCustomer customer = TransactionUtil.getCustomer("ts11-1");
        cusCustomerEntityService.save(customer);

        transactionalService.saveMandatory();

        System.out.println("End");

    }

    public void saveN2S() {

        CusCustomer customer = TransactionUtil.getCustomer("ts14");
        cusCustomerEntityService.save(customer);

        transactionalService2.saveSports();

        System.out.println("End");
    }

    public long doSomething() {

        Date startDate = new Date();

        for (int i = 0; i < 99999; i++){
            CusCustomer cusCustomer = nonTransactionalConstantService.findById(1L);
        }

        Date endDate = new Date();

        long diff = endDate.getTime() - startDate.getTime();

        return diff;
    }

    public void saveNon2Never() {

        CusCustomer customer = TransactionUtil.getCustomer("ts20");
        cusCustomerEntityService.save(customer);

        nonTransactionalConstantService.saveNever();

        System.out.println("end");
    }
}
