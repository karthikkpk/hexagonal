package com.hexagonal.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.dto.AccountDto;
import com.hexagonal.dto.ResponseDto;
import com.hexagonal.ports.api.AccountServicePort;
import com.hexagonal.util.AppConstant;;

/**
 * @author Karthik.P
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private final Logger log = LogManager.getLogger(AccountController.class);

	@Autowired
	private AccountServicePort accountService;

	/**
	 * Method to get All account details
	 * 
	 * @return
	 */
	@GetMapping("/getAccounts")
	public ResponseDto getAccounts() {

		final ResponseDto outVo = new ResponseDto();
		try {
			log.info("getAccounts --> Start");

			final List<AccountDto> accountDtoList = accountService.getAccounts();
			outVo.setAccountDtoList(accountDtoList);
			outVo.setMessage(AppConstant.SUCCESS);
			log.info("getAccounts --> End");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			outVo.setMessage("ERROR : " + e.getMessage());
		}
		return outVo;

	}

	/**
	 * Method to get account by account number.
	 * 
	 * @param accNo
	 * @return
	 */
	@GetMapping("/getAccByAccountNo")
	public ResponseDto getAccByAccountNo(@RequestParam("accNo") Integer accNo) {

		ResponseDto outDto = new ResponseDto();
		try {
			log.info("getAccByAccountNo --> Start");

			if (accNo == null || accNo == 0) {
				throw new Exception("Please enter Account Number");
			}

			final AccountDto accountDto = accountService.getAccByAccountNo(accNo);
			outDto.setMessage(AppConstant.SUCCESS);
			outDto.setAccountDto(accountDto);

			log.info("getAccByAccountNo --> End");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			outDto.setMessage("ERROR : " + e.getMessage());
		}
		return outDto;
	}

	/**
	 * Method to delete account by account number.
	 * 
	 * @param accNo
	 * @return
	 */
	@GetMapping("/deleteAccByAccountNo")
	public String deleteAccByAccountNo(@RequestParam("accNo") Integer accNo) {

		try {
			log.info("deleteAccByAccountNo --> Start");

			if (accNo == null || accNo == 0) {
				throw new Exception("Please enter Account Number");
			}

			accountService.deleteAccByAccountNo(accNo);
			log.info("deleteAccByAccountNo --> End");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			return "ERROR : " + e.getMessage();
		}
		return "SUCCESS";
	}

	/**
	 * Method to save in single entity
	 * 
	 * @param accountDto
	 * @return
	 */
	@PostMapping("/saveUpdateAccount")
	public ResponseDto saveUpdateAccount(@RequestBody AccountDto accountDto) {

		final ResponseDto outVo = new ResponseDto();
		try {
			log.info("saveUpdateAccount --> Start");

			if (accountDto == null) {
				throw new Exception("No Record to save/update");
			}

			String errorMsg = validateInputs(accountDto);

			if (errorMsg.length() > 0) {
				throw new Exception("Mandatory Value is missing. Details : " + errorMsg.toString());
			}

			AccountDto accDto = accountService.saveUpdateAccount(accountDto);
			outVo.setMessage(AppConstant.SUCCESS);
			outVo.setAccountDto(accDto);
			log.info("saveUpdateAccount --> End");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			outVo.setMessage(AppConstant.ERROR + " : " + e.getMessage());
		}
		return outVo;
	}

	/**
	 * Method to save in list of entity.
	 * 
	 * @param accountDtoList
	 * @return
	 */
	@PostMapping("/saveUpdateAccountBulk")
	public String saveUpdateAccountBulk(@RequestBody List<AccountDto> accountDtoList) {

		try {
			log.info("saveUpdateAccountBulk --> Start");

			if (accountDtoList == null) {
				throw new Exception("No Record to save/update");
			}

			StringBuilder errorMsg = new StringBuilder();

			for (AccountDto accountDto : accountDtoList) {

				String errMsg = validateInputs(accountDto);
				if (errMsg.length() > 0) {
					errorMsg.append("Row :: " + errMsg);
				}
			}

			if (errorMsg.length() > 0) {
				throw new Exception("Mandatory Value is missing. Details : " + errorMsg.toString());
			}

			accountService.saveUpdateAccountBulk(accountDtoList);
			log.info("saveUpdateAccountBulk --> End");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			return "ERROR : " + e.getMessage();
		}
		return "SUCCESS";
	}

	/**
	 * Method to delete all records.
	 * 
	 * @return
	 */
	@GetMapping("/deleteAll")
	public String deleteAll(@RequestParam("deleteFlag") Integer deleteFlag) {

		try {
			log.info("deleteAll --> Start");

			if (deleteFlag == null || deleteFlag == 0) {
				throw new Exception("Flag is required!");
			}
			accountService.deleteAll();
			log.info("deleteAll --> End");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error : " + e.getMessage());
			return "ERROR : " + e.getMessage();
		}
		return "Records Deleted successfully";
	}

	/**
	 * Method to validate inputs for save/update account
	 * 
	 * @param accountDto
	 * @return
	 */
	private String validateInputs(final AccountDto accountDto) {

		final StringBuilder errorMsg = new StringBuilder();

		if (accountDto.getAccName() == null || accountDto.getAccName().isEmpty()) {
			errorMsg.append("'Account Name'");
		}

		if (accountDto.getAccNo() == null) {
			if (errorMsg.length() > 0) {
				errorMsg.append(",");
			}
			errorMsg.append("'Account Number'");
		}

		if (accountDto.getAddress() == null || accountDto.getAddress().isEmpty()) {
			if (errorMsg.length() > 0) {
				errorMsg.append(",");
			}
			errorMsg.append("'Address'");
		}

		if (accountDto.getPhone() == null || accountDto.getPhone().isEmpty()) {
			if (errorMsg.length() > 0) {
				errorMsg.append(",");
			}
			errorMsg.append("'Phone'");
		}

		if (accountDto.getAccType() == null) {
			if (errorMsg.length() > 0) {
				errorMsg.append(",");
			}
			errorMsg.append("'Account Type'");
		}

		if (accountDto.getOpenDate() == null) {
			if (errorMsg.length() > 0) {
				errorMsg.append(",");
			}
			errorMsg.append("'Open Date'");
		}
		return errorMsg.toString();
	}

}
