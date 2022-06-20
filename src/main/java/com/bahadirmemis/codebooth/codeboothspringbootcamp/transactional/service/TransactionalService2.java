package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Transactional
@Service
@RequiredArgsConstructor
public class TransactionalService2 {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN() {

        CusCustomer customer = TransactionUtil.getCustomer("ts9-2");
        cusCustomerEntityService.save(customer);

        System.out.println("end");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN(int i) {

        CusCustomer customer = TransactionUtil.getCustomer("ts10-" + i);
        cusCustomerEntityService.save(customer);

        if (i == 7){
            throw new RuntimeException("Error");
        }

        System.out.println("end ->" + i);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMandatory() {

        CusCustomer customer = TransactionUtil.getCustomer("ts12-M");
        cusCustomerEntityService.save(customer);

        System.out.println("End");

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveSports() {
        CusCustomer customer = TransactionUtil.getCustomer("ts13-S");
        cusCustomerEntityService.save(customer);

        System.out.println("End");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveNested() {
        CusCustomer customer = TransactionUtil.getCustomer("ts16-N");
        cusCustomerEntityService.save(customer);

        System.out.println("End");
    }
}
