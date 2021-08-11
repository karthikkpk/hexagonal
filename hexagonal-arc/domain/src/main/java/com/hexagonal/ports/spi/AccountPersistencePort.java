package com.hexagonal.ports.spi;

import java.util.List;

import com.hexagonal.dto.AccountDto;

public interface AccountPersistencePort {

	List<AccountDto> getAccounts() throws Exception;

	AccountDto getAccByAccountNo(final Integer accNo) throws Exception;

	AccountDto saveUpdateAccount(final AccountDto accountDto) throws Exception;

	void deleteAccByAccountNo(final Integer accNo) throws Exception;

	void deleteAll() throws Exception;

	void saveUpdateAccountBulk(final List<AccountDto> accountDtoList) throws Exception;

}
