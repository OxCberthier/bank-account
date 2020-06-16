package com.cberthier.bankaccount.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_operation_account_id"), nullable = false)
    private Account account;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private OperationTypeEnum operationType;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Date date;

    //Default Hibernate constructor
    public Operation() {
    }

    public Operation(Account account, double amount, OperationTypeEnum operationType, Date date) {
        this.account = account;
        this.amount = amount;
        this.operationType = operationType;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public Date getDate() {
        return date;
    }
}
