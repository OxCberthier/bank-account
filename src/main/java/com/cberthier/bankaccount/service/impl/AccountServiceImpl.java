package com.cberthier.bankaccount.service.impl;

import com.cberthier.bankaccount.domain.model.Account;
import com.cberthier.bankaccount.domain.model.Operation;
import com.cberthier.bankaccount.domain.model.OperationTypeEnum;
import com.cberthier.bankaccount.domain.repository.AccountCrudRepository;
import com.cberthier.bankaccount.domain.repository.OperationPagingAndSortingRepository;
import com.cberthier.bankaccount.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AccountServiceImpl implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountCrudRepository accountCrudRepository;

    private OperationPagingAndSortingRepository operationPagingAndSortingRepository;

    public AccountServiceImpl(AccountCrudRepository accountCrudRepository,
                              OperationPagingAndSortingRepository operationPagingAndSortingRepository) {
        this.accountCrudRepository = accountCrudRepository;
        this.operationPagingAndSortingRepository = operationPagingAndSortingRepository;
    }

    @Override
    public Account addOperation(Account account, double operationAmount, OperationTypeEnum operationTypeEnum) {
        logger.debug("Add Operation -> Account {}, Amount: {}, Type: {}", account.getName(), operationAmount, operationTypeEnum);
        logger.debug("Old balance -> {}", account.getBalance());

        account.setBalance(Double.sum(account.getBalance(), operationAmount));

        logger.debug("New balance -> {}", account.getBalance());

        Operation operation = new Operation(account, operationAmount, operationTypeEnum, Calendar.getInstance().getTime());
        operationPagingAndSortingRepository.save(operation);
        return operation.getAccount();
    }
}
