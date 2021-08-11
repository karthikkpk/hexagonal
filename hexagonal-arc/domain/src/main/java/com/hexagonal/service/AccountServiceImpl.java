package com.hexagonal.service;

import java.util.List;

import com.hexagonal.dto.AccountDto;
import com.hexagonal.ports.api.AccountServicePort;
import com.hexagonal.ports.spi.AccountPersistencePort;

public class AccountServiceImpl implements AccountServicePort {

	private AccountPersistencePort accountPersistencePort;

	public AccountServiceImpl(AccountPersistencePort accountPersistencePort) {
		this.accountPersistencePort = accountPersistencePort;
	}

	@Override
	public List<AccountDto> getAccounts() throws Exception {
		return accountPersistencePort.getAccounts();
	}

	@Override
	public AccountDto getAccByAccountNo(Integer accNo) throws Exception {
		return accountPersistencePort.getAccByAccountNo(accNo);
	}

	@Override
	public AccountDto saveUpdateAccount(AccountDto accountDto) throws Exception {
		return accountPersistencePort.saveUpdateAccount(accountDto);
	}

	@Override
	public void deleteAccByAccountNo(Integer accNo) throws Exception {
		accountPersistencePort.deleteAccByAccountNo(accNo);

	}

	@Override
	public void deleteAll() throws Exception {
		accountPersistencePort.deleteAll();
	}

	@Override
	public void saveUpdateAccountBulk(List<AccountDto> accountDtoList) throws Exception {
		accountPersistencePort.saveUpdateAccountBulk(accountDtoList);
	}

}
