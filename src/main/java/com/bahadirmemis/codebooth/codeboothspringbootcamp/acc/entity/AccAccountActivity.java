package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccActivityType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Entity
@Table(name = "ACC_ACCOUNT_ACTIVITY")
@Getter
@Setter
public class AccAccountActivity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccountActivity", sequenceName = "ACC_ACCOUNT_ACTIVITY_ID_SEQ")
    @GeneratedValue(generator = "AccAccountActivity")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_ACC_ACCOUNT")
//    private AccAccount accAccount;

    @Column(name = "ID_ACC_ACCOUNT")
    private Long accAccountId;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVITY_TYPE", length = 30)
    private EnumAccActivityType activityType;

}
