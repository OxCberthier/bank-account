package com.cberthier.bankaccount.service;

import com.cberthier.bankaccount.domain.model.Account;
import com.cberthier.bankaccount.domain.model.OperationTypeEnum;

public interface AccountService {

    Account addOperation(Account account, double operationAmount, OperationTypeEnum operationTypeEnum);
}
