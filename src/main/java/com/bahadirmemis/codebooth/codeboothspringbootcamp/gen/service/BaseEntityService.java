package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.entity.BaseAdditionalFields;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.entity.BaseEntity;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository<E, Long>> {

    private final D dao;

    public D getDao() {
        return dao;
    }

    public List<E> findAll(){
        return dao.findAll();
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }

    public E findByIdWithControl(Long id){
        Optional<E> optionalEntity = dao.findById(id);

        E entity;
        if (optionalEntity.isPresent()){
            entity = optionalEntity.get();
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }

        return entity;
    }

    public E save(E entity){

        setAdditionalFields(entity);

        entity = dao.save(entity);

        return entity;
    }

    private void setAdditionalFields(E entity) {
        BaseAdditionalFields additionalFields = entity.getAdditionalFields();

        if (additionalFields == null){
            additionalFields = new BaseAdditionalFields();
            entity.setAdditionalFields(additionalFields);
        }

        if (entity.getId() == null){
            // new record
            additionalFields.setCreateDate(new Date());
//            additionalFields.setCreatedBy(); //TODO: after JWT
        }

        additionalFields.setUpdateDate(new Date());
//        additionalFields.setUpdatedBy(); //TODO: after JWT
    }

    public void delete(Long id){
        dao.deleteById(id);
    }

    public void delete(E entity){
        dao.delete(entity);
    }

    public boolean isExist(Long id){
        return dao.existsById(id);
    }
}
