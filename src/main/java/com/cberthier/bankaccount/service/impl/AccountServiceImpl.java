package com.cberthier.bankaccount.service.impl;

import com.cberthier.bankaccount.domain.model.Account;
import com.cberthier.bankaccount.domain.model.OperationTypeEnum;
import com.cberthier.bankaccount.domain.repository.AccountCrudRepository;
import com.cberthier.bankaccount.domain.repository.OperationPagingAndSortingRepository;
import com.cberthier.bankaccount.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountCrudRepository accountCrudRepository;

    private OperationPagingAndSortingRepository operationPagingAndSortingRepository;

    public AccountServiceImpl(AccountCrudRepository accountCrudRepository, OperationPagingAndSortingRepository operationPagingAndSortingRepository) {
        this.accountCrudRepository = accountCrudRepository;
        this.operationPagingAndSortingRepository = operationPagingAndSortingRepository;
    }

    @Override
    public Account addOperation(Account account, double operationAmount, OperationTypeEnum operationTypeEnum) {
        return null;
    }
}
