package com.AccountService.mappers;

import org.springframework.stereotype.Component;

import com.AccountService.DTO.customerDetailsDto;
import com.AccountService.DTO.customersDTO;
import com.AccountService.Entity.Customer;
@Component
public class customerMapper {
	
	public Customer mapToCustomer(Customer customer,customersDTO customerdto )
	{
		customer.setName(customerdto.getName());
		customer.setEmail(customerdto.getEmail());
		customer.setMobile_number(customerdto.getMobileNumber());
		return customer;
		
	}
	
	public customersDTO mapToCustomerDTO(Customer customer,customersDTO customerdto )
	{
		customerdto.setName(customer.getName());
		customerdto.setEmail(customer.getEmail());
		customerdto.setMobileNumber(customer.getMobile_number());
		return customerdto;
		
	}
	
	public customerDetailsDto mapToCustomerDetailsDTO(Customer customer,customerDetailsDto customerDetailsdto )
	{
		customerDetailsdto.setName(customer.getName());
		customerDetailsdto.setEmail(customer.getEmail());
		customerDetailsdto.setMobileNumber(customer.getMobile_number());
		return customerDetailsdto;
		
	}
}
