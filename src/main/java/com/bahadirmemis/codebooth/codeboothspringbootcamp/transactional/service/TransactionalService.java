package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private final TransactionalService2 transactionalService2;
    private final TransactionalConstantService transactionalConstantService;
    private final NonTransactionalConstantService nonTransactionalConstantService;

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

    public void saveT2RN() {

        CusCustomer customer = TransactionUtil.getCustomer("ts8-1");
        cusCustomerEntityService.save(customer);

        saveRN();

        System.out.println("end");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN() {

        CusCustomer customer = TransactionUtil.getCustomer("ts8-2");
        cusCustomerEntityService.save(customer);

        System.out.println("end");
    }

    public void saveT2RNWithDifferentBean() {

        CusCustomer customer = TransactionUtil.getCustomer("ts9-1");
        cusCustomerEntityService.save(customer);

        transactionalService2.saveRN();

        System.out.println("end");
    }

    public void saveT2RNButError() {

        CusCustomer customer = TransactionUtil.getCustomer("ts10");
        cusCustomerEntityService.save(customer);

        for (int i = 0; i < 10; i++){

            try {
                transactionalService2.saveRN(i);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("end");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMandatory() {

        CusCustomer customer = TransactionUtil.getCustomer("ts11-M");
        cusCustomerEntityService.save(customer);

        System.out.println("End");

    }

    public void saveT2M() {

        CusCustomer customer = TransactionUtil.getCustomer("ts12");
        cusCustomerEntityService.save(customer);

        transactionalService2.saveMandatory();

        System.out.println("End");

    }

    public void saveT2S() {

        CusCustomer customer = TransactionUtil.getCustomer("ts13");
        cusCustomerEntityService.save(customer);

        transactionalService2.saveSports();

        System.out.println("End");

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long doSomething() {

        Date startDate = new Date();

        for (int i = 0; i < 99999; i++){
            CusCustomer cusCustomer = transactionalConstantService.findById(1L);
        }

        Date endDate = new Date();

        long diff = endDate.getTime() - startDate.getTime();

        return diff;
    }

    public void saveNested() {

        transactionalService2.saveNested();

        System.out.println("End");
    }

    public void saveT2TButError() {

        CusCustomer customer = TransactionUtil.getCustomer("ts18");
        cusCustomerEntityService.save(customer);

        for (int i = 0; i < 10; i++){
            try {
                transactionalService2.save(i);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("End");
    }

    public long doSomethingWithNewTransaction() {

        Date startDate = new Date();

        for (int i = 0; i < 9999; i++){
            CusCustomer cusCustomer = transactionalConstantService.findByIdRN(1L);
        }

        Date endDate = new Date();

        long diff = endDate.getTime() - startDate.getTime();

        return diff;
    }

    public void saveT2Never() {

        CusCustomer customer = TransactionUtil.getCustomer("ts21");
        cusCustomerEntityService.save(customer);

        nonTransactionalConstantService.saveNever();

        System.out.println("end");
    }
}
