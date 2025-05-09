package com.AccountService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AccountService.Entity.Customer;

public interface customerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByMobileNumber(String mobileNumber);

}
