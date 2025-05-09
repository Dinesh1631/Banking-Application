package com.AccountService.Services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.AccountService.DTO.CardsDto;
import com.AccountService.DTO.LoansDto;
import com.AccountService.DTO.accountsDTO;
import com.AccountService.DTO.customerDetailsDto;
import com.AccountService.Entity.Accounts;
import com.AccountService.Entity.Customer;
import com.AccountService.Exceptions.ResourceNotFountException;
import com.AccountService.Repository.accountsRepository;
import com.AccountService.Repository.customerRepository;
import com.AccountService.Services.IcustomService;
import com.AccountService.Services.FeignClients.LoansFeignClient;
import com.AccountService.Services.FeignClients.cardsFeignClient;
import com.AccountService.mappers.accountMapper;
import com.AccountService.mappers.customerMapper;
@Service
public class customServiceimpl implements IcustomService {
	private accountsRepository accountrepo;
	private customerRepository customerrepo;
	private customerMapper customermapper;
	private accountMapper accountmapper;
	private LoansFeignClient loansClient;
	private cardsFeignClient cardsClient;
	
	public customServiceimpl(accountsRepository accountrepo, customerRepository customerrepo,
			customerMapper customermapper, accountMapper accountmapper, LoansFeignClient loansClient,
			cardsFeignClient cardsClient) {
		this.accountrepo = accountrepo;
		this.customerrepo = customerrepo;
		this.customermapper = customermapper;
		this.accountmapper = accountmapper;
		this.loansClient = loansClient;
		this.cardsClient = cardsClient;
	}

	@Override
	public customerDetailsDto fetchAllDetailsOfCustomers(String mobileNumber,String trace_id) {
		Customer customer = customerrepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFountException("customer", "mobileNumber", mobileNumber));
		Accounts account = accountrepo.findBycustomerid(customer.getCustomer_id())
				.orElseThrow(() -> new ResourceNotFountException("account", "customer", mobileNumber));
		accountsDTO accountdto = accountmapper.mapToAccountDTO(account, new accountsDTO());
		customerDetailsDto customerdetails = customermapper.mapToCustomerDetailsDTO(customer,new customerDetailsDto());
		customerdetails.setAccountsdto(accountdto);
		ResponseEntity<LoansDto> loansdto = loansClient.fetchCardDetails(mobileNumber,trace_id);
		if(null!=loansdto) {
			customerdetails.setLoansdto(loansdto.getBody());
		}
		ResponseEntity<CardsDto> cardsdto = cardsClient.fetchCardDetails(mobileNumber,trace_id);
		if(null!=cardsdto) {
		customerdetails.setCardsdto(cardsdto.getBody());
		}
		return customerdetails;
	}

}
