package com.bankaccount;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.CurrentAccountDto;
import com.bankaccount.dto.CustomerDto;
import com.bankaccount.dto.OperationDto;
import com.bankaccount.service.AccountService;
import com.bankaccount.service.CustomerService;
import com.bankaccount.service.OperationService;

@SpringBootApplication
public class BankAccountApplication implements CommandLineRunner {

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

	@Autowired
	OperationService operationService;

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create a customer
		CustomerDto customerDelta = new CustomerDto("Michae", "Delta", new Date());
		customerDelta = customerService.createCustomer(customerDelta);

		// create a current account
		AccountDto currentAccount = new CurrentAccountDto(new Date(), customerDelta, 400L);
		accountService.createAccount(currentAccount);

		// US 1 - deposit money
		operationService.deposit(1L, 190.5);
		operationService.deposit(1L, 934.5);
		operationService.deposit(1L, 148.1);
		operationService.deposit(1L, 34.59);
		operationService.deposit(1L, 8400.37);
		operationService.deposit(1L, 837.59);

		// US 2 - withdraw money
		operationService.withdrawal(1L, 87.34);
		operationService.withdrawal(1L, 1093.9);
		operationService.withdrawal(1L, 843.23);
		operationService.withdrawal(1L, 43.9);

		// US 3 - see the history of operations

		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%10s %20s %20s", "OPERATION TYPE", "AMOUNT", "OPERATION DATE");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

		List<OperationDto> operations = operationService.showAllOperations(1L);
		for (OperationDto operation : operations) {
			System.out.format("%10s %23s %28s", operation.getOperationType(), operation.getAmount(),
					operation.getOperationDate());
			System.out.println();
		}

		System.out.println("-----------------------------------------------------------------------------");

		double balance = accountService.findByAccountId(1L).getBalance();
		System.out.println("Balance = " + balance);

	}
}
