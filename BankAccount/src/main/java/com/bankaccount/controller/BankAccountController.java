package com.bankaccount.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.dto.AccountDto;
import com.bankaccount.dto.OperationDto;
import com.bankaccount.exception.OperationException;
import com.bankaccount.service.OperationService;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

	final static Logger logger = Logger.getLogger(BankAccountController.class);

	@Autowired
	OperationService operationService;

	/**
	 * Method to show all operations
	 */
	@RequestMapping(value = "showAllOperations/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> showAllOperations(@PathVariable final Long id) {
		try {
			List<OperationDto> operations = operationService.showAllOperations(id);
			return new ResponseEntity<>(operations, operations.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (OperationException e) {
			logger.error(e.fillInStackTrace());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method to deposit money
	 */
	@RequestMapping(value = "deposit", method = RequestMethod.POST)
	public ResponseEntity<Object> deposit(@RequestParam("accountId") final Long accountId,
			@RequestParam("amount") final double amount) {
		try {
			AccountDto account = operationService.deposit(accountId, amount);
			return new ResponseEntity<>(account, account == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (OperationException e) {
			logger.error(e.fillInStackTrace());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method to withdraw money
	 */
	@RequestMapping(value = "withdrawal", method = RequestMethod.POST)
	public ResponseEntity<Object> withdrawal(@RequestParam("accountId") final Long accountId,
			@RequestParam("amount") final double amount) {
		try {
			AccountDto accountDto = operationService.withdrawal(accountId, amount);
			return new ResponseEntity<>(accountDto, accountDto == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
		} catch (OperationException e) {
			logger.error(e.fillInStackTrace());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
