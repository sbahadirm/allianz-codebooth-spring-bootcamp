package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.entityservice;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dao.CusCustomerDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CusCustomerEntityService {

    private final CusCustomerDao cusCustomerDao;

    public List<CusCustomer> findAll(){
        return cusCustomerDao.findAll();
    }

    public Optional<CusCustomer> findById(Long id){
        return cusCustomerDao.findById(id);
    }

    public CusCustomer findByIdWithControl(Long id){
        return cusCustomerDao.findById(id).orElseThrow();
    }

    public CusCustomer save(CusCustomer cusCustomer){
        return cusCustomerDao.save(cusCustomer);
    }

    public void delete(Long id){
        cusCustomerDao.deleteById(id);
    }

    public void delete(CusCustomer cusCustomer){
        cusCustomerDao.delete(cusCustomer);
    }

    public boolean isExist(Long id){
        return cusCustomerDao.existsById(id);
    }
}
