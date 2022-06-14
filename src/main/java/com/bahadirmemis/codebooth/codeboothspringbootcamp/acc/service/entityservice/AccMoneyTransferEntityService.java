package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dao.AccMoneyTransferDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccMoneyTransfer;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class AccMoneyTransferEntityService extends BaseEntityService<AccMoneyTransfer, AccMoneyTransferDao> {

    public AccMoneyTransferEntityService(AccMoneyTransferDao dao) {
        super(dao);
    }
}
