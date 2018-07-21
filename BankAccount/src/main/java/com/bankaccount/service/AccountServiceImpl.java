package com.bankaccount.service;

import javax.security.auth.login.AccountException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.dao.AccountDao;
import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.CurrentAccountDto;
import com.bankaccount.entity.Account;
import com.bankaccount.entity.CurrentAccount;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AccountDao accountDao;

	/**
	 * Method to get account by its id
	 */
	@Override
	public AccountDto findByAccountId(final Long id) {
		Account account = accountDao.findByAccountId(id);
		if (account == null) {
			return null;
		}
		return modelMapper.map(account, AccountDto.class);
	}

	/**
	 * Method to create a new account
	 * 
	 * @throws AccountException
	 */
	@Override
	public void createAccount(final AccountDto accountDto) throws AccountException {
		if (accountDto == null) {
			throw new AccountException("You could not create a null account");
		}
		Account account = null;
		if (accountDto instanceof CurrentAccountDto) {
			account = modelMapper.map(accountDto, CurrentAccount.class);
		}
		accountDao.createAccount(account);
	}

}
