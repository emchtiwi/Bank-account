package com.bankaccount.service;

import javax.security.auth.login.AccountException;

import com.bankaccount.dto.AccountDto;

public interface AccountService {

	AccountDto findByAccountId(final Long id);

	void createAccount(final AccountDto accountDto) throws AccountException;

}
