package com.cberthier.bankaccount.impl;


import com.cberthier.bankaccount.domain.model.Account;
import com.cberthier.bankaccount.domain.model.Client;
import com.cberthier.bankaccount.domain.model.Operation;
import com.cberthier.bankaccount.domain.model.OperationTypeEnum;
import com.cberthier.bankaccount.domain.repository.AccountCrudRepository;
import com.cberthier.bankaccount.domain.repository.OperationPagingAndSortingRepository;
import com.cberthier.bankaccount.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AccountServiceImplTest {

    private static final double initBalance = 0;
    private static AccountServiceImpl accountService;
    @Mock
    private static AccountCrudRepository accountCrudRepositoryMock;
    @Mock
    private static OperationPagingAndSortingRepository operationPagingAndSortingRepository;
    @Mock
    private static Client clientMock;
    private static Account account;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        account = new Account("MyAccount", clientMock, initBalance);
        accountService = new AccountServiceImpl(accountCrudRepositoryMock, operationPagingAndSortingRepository);
    }

    @Test
    public void addDepositOperationOnAccount() {
        double operationAmount = 99.9;
        ArgumentCaptor<Operation> operationCaptor = ArgumentCaptor.forClass(Operation.class);

        Account accountReturned = accountService.addOperation(account, operationAmount, OperationTypeEnum.DEPOSIT);

        Mockito.verify(operationPagingAndSortingRepository).save(operationCaptor.capture());
        Operation operationCaptorValue = operationCaptor.getValue();
        //Verify balance account returned is updated
        Assertions.assertEquals(Double.sum(initBalance, operationAmount), accountReturned.getBalance());

        //Verify balance account save in db is updated
        Assertions.assertEquals(Double.sum(initBalance, operationAmount), operationCaptorValue.getAccount().getBalance());
    }
}
