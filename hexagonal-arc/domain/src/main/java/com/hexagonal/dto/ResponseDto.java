package com.hexagonal.dto;

import java.util.List;

public class ResponseDto {

	private String message;
	private AccountDto accountDto;
	private List<AccountDto> accountDtoList;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the accountDto
	 */
	public AccountDto getAccountDto() {
		return accountDto;
	}

	/**
	 * @param accountDto the accountDto to set
	 */
	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	/**
	 * @return the accountDtoList
	 */

	public List<AccountDto> getAccountDtoList() {
		return accountDtoList;
	}

	/**
	 * @param accountDtoList the accountDtoList to set
	 */
	public void setAccountDtoList(List<AccountDto> accountDtoList) {
		this.accountDtoList = accountDtoList;
	}
}
