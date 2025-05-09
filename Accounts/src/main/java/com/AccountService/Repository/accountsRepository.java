package com.AccountService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.AccountService.Entity.Accounts;

import jakarta.transaction.Transactional;

public interface accountsRepository extends JpaRepository<Accounts, Long> {

	public Optional<Accounts> findBycustomerid(Long customer_id);
	
	public  Optional<Accounts> findByAccountNumber(Long accountNumber);
    @Transactional
    @Modifying
	public void deleteBycustomerid(Long customer_id);
}
