package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dao;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccMoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface AccMoneyTransferDao extends JpaRepository<AccMoneyTransfer, Long> {
}
