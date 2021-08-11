package com.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.adapters.AccountJpaAdapter;
import com.hexagonal.ports.api.AccountServicePort;
import com.hexagonal.ports.spi.AccountPersistencePort;
import com.hexagonal.service.AccountServiceImpl;

@Configuration
public class AccountConfig {

	@Bean
	public AccountPersistencePort accountPersistence() {
		return new AccountJpaAdapter();
	}

	@Bean
	public AccountServicePort accountService() {
		return new AccountServiceImpl(accountPersistence());
	}
}
