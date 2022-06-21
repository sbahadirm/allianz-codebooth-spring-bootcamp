package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dao.CusCustomerDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CusCustomerEntityService extends BaseEntityService<CusCustomer, CusCustomerDao> {

    public CusCustomerEntityService(CusCustomerDao dao) {
        super(dao);
    }

    public CusCustomer findByIdentityNo(Long identityNo){
        return getDao().findByIdentityNo(identityNo);
    }
}
