package com.AccountService.Services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.AccountService.DTO.accountsDTO;
import com.AccountService.DTO.customersDTO;
import com.AccountService.Entity.Accounts;
import com.AccountService.Entity.Customer;
import com.AccountService.Exceptions.ResourceNotFountException;
import com.AccountService.Exceptions.customerAlreayExistException;
import com.AccountService.Repository.accountsRepository;
import com.AccountService.Repository.customerRepository;
import com.AccountService.Services.IAccountService;
import com.AccountService.commonConstants.AccountConstants;
import com.AccountService.mappers.accountMapper;
import com.AccountService.mappers.customerMapper;

@Service
public class accountServiceImpl implements IAccountService {

	private accountsRepository accountrepository;
	private customerRepository customerrepository;
	private customerMapper customermapper;
	private accountMapper accountmapper;

	public accountServiceImpl(accountsRepository accountrepository, customerRepository customerrepository,
			customerMapper customermapper, accountMapper accountmapper) {
		this.accountrepository = accountrepository;
		this.customerrepository = customerrepository;
		this.customermapper = customermapper;
		this.accountmapper = accountmapper; 
	}

	@Override
	public void createUser(customersDTO customerdto) {
		Optional<Customer> customerByNumber = customerrepository.findByMobileNumber(customerdto.getMobileNumber());
		if (customerByNumber.isPresent()) {
			throw new customerAlreayExistException(
					"customer with mobilenumber Already Exist :" + customerdto.getMobileNumber());
		}
		Customer customer = customermapper.mapToCustomer(new Customer(), customerdto);

		Customer savedcustomer = customerrepository.save(customer);
		customerrepository.flush();
		Accounts account = createAccount(savedcustomer);
		accountrepository.save(account);
		accountrepository.flush();
	}

	private Accounts createAccount(Customer customer) {
		Accounts account = new Accounts();
		account.setCustomer_id(customer.getCustomer_id());
		Long AccountNumber = 1000000000L + new Random().nextInt(900000000);
		account.setAccount_number(AccountNumber);
		account.setAccount_type(AccountConstants.ACCOUNTTYPE);
		account.setBranch_address(AccountConstants.ADDRESS);

		return account;
	}

	@Override
	public customersDTO fetchCustomer(String mobileNumber) {
		// Customer customer =
		// customerrepository.findByMobileNumber(mobileNumber).get();
		Customer customer = customerrepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFountException("customer", "mobileNumber", mobileNumber));
		Accounts account = accountrepository.findBycustomerid(customer.getCustomer_id())
				.orElseThrow(() -> new ResourceNotFountException("account", "customer", mobileNumber));
		accountsDTO accountdto = accountmapper.mapToAccountDTO(account, new accountsDTO());
		customersDTO customerdto = customermapper.mapToCustomerDTO(customer, new customersDTO());
		customerdto.setAccountsdto(accountdto);
		return customerdto;
	}

	@Override
	public boolean updateCustmor(customersDTO customerdto) {
		boolean isUpdated = false;
		accountsDTO accountdto = customerdto.getAccountsdto();
		if (accountdto != null) {
			Accounts account = accountrepository.findByAccountNumber(accountdto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFountException("account", "accountNumber",
							Long.toString(accountdto.getAccountNumber())));
			// updating the account with accountDto account we got.
			accountmapper.mapToAccount(account, accountdto);
			account = accountrepository.save(account);

			Long customerId = account.getCustomer_id();

			Customer customer = customerrepository.findById(customerId).orElseThrow(
					() -> new ResourceNotFountException("customer", "customerId", Long.toString(customerId)));
			customermapper.mapToCustomer(customer, customerdto);
			customer = customerrepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteCustomer(String mobileNumber) {
		boolean isDeleted = false;
		Customer customer = customerrepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFountException("customer", "mobileNumber", mobileNumber));
		accountrepository.deleteBycustomerid(customer.getCustomer_id());
		customerrepository.deleteById(customer.getCustomer_id());
		isDeleted = true;
		return isDeleted;
	}

}
