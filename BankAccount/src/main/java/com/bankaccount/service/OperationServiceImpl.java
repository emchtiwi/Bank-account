package com.bankaccount.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.dao.AccountDao;
import com.bankaccount.dao.OperationDao;
import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.OperationDto;
import com.bankaccount.entity.Account;
import com.bankaccount.entity.CurrentAccount;
import com.bankaccount.entity.Deposit;
import com.bankaccount.entity.Operation;
import com.bankaccount.entity.Withdrawal;
import com.bankaccount.exception.OperationException;
import com.bankaccount.service.mapper.ModelMapper;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	OperationDao operationDao;

	@Autowired
	AccountDao accountDao;

	@Autowired
	ModelMapper modelMapper;

	/*
	 * Method to deposit money
	 * 
	 * @throws OperationException
	 */
	@Override
	public AccountDto deposit(final Long accountId, final double amount) throws OperationException {
		Account account = accountDao.findByAccountId(accountId);
		if (account == null) {
			throw new OperationException("Account cannot be found");
		}
		Operation deposit = new Deposit(amount, new Date(), account);
		operationDao.createOperation(deposit);
		account.setBalance(account.getBalance() + amount);
		account = accountDao.createAccount(account);
		return modelMapper.map(account, AccountDto.class);
	}

	/**
	 * Method to withdraw money
	 * 
	 * @throws OperationException
	 */
	@Override
	public AccountDto withdrawal(final Long accountId, final double amount) throws OperationException {
		double overdraftTmp = 0;
		Account account = accountDao.findByAccountId(accountId);
		if (account == null) {
			throw new OperationException("Account cannot be found");
		}
		if (account instanceof CurrentAccount) {
			overdraftTmp = ((CurrentAccount) account).getOverdraft();
			if ((account.getBalance() + overdraftTmp) < amount) {
				throw new OperationException("Unavailable funds");
			}
		}
		Operation withdrawal = new Withdrawal(amount, new Date(), account);
		operationDao.createOperation(withdrawal);
		account.setBalance(account.getBalance() - amount);
		account = accountDao.createAccount(account);
		return modelMapper.map(account, AccountDto.class);
	}

	/**
	 * Method to show all operations
	 * 
	 * @throws OperationException
	 */
	@Override
	public List<OperationDto> showAllOperations(final Long acountId) throws OperationException {
		Account account = accountDao.findByAccountId(acountId);
		if (account == null) {
			throw new OperationException("Accout cannot be found");
		}
		List<Operation> operations = ListUtils.emptyIfNull(account.getOperations());
		return (List<OperationDto>) modelMapper.mapCollection(operations, OperationDto.class);
	}

}
