package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts, Integer> {

	List<Accounts> findByAmountGreaterThan(int amount);

	List<Accounts> findByAmountLessThan(int amount);

    Optional<Accounts> findByAccNo(int accNo);;
	
	
}
