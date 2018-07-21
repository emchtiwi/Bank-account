package com.bankaccount.service;

import java.util.List;

import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.OperationDto;
import com.bankaccount.exception.OperationException;

public interface OperationService {

	AccountDto deposit(final Long accountId, final double amount) throws OperationException;

	AccountDto withdrawal(final Long accountId, final double amount) throws OperationException;

	List<OperationDto> showAllOperations(final Long acountId) throws OperationException;
}
