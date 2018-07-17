package com.bankaccount.dao;

import com.bankaccount.entity.Operation;

public interface OperationDao {

	Operation findByOperationNumber(final Long number);

	Operation createOperation(final Operation operation);

}
