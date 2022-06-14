package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.service.entityservice;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dao.AccAccountActivityDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class AccAccountActivityEntityService extends BaseEntityService<AccAccountActivity, AccAccountActivityDao> {

    public AccAccountActivityEntityService(AccAccountActivityDao dao) {
        super(dao);
    }
}
