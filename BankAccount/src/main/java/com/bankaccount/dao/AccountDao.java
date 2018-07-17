package com.bankaccount.dao;

import com.bankaccount.entity.Account;

public interface AccountDao {

	Account findByAccountId(final Long id);

	Account createAccount(final Account account);

}