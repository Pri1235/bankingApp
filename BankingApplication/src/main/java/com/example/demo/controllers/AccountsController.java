package com.example.demo.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Accounts;
import com.example.demo.repositories.AccountsRepo;

@RestController
public class AccountsController {

	@Autowired
	AccountsRepo repo;
	
	@PostMapping("/login")
	public Boolean getLoginDetails(int cid, String password) {
		Optional<Accounts> a = repo.findById(cid);
		 return a.map(account -> account.getPassword().equals(password)).orElse(false);
	}
	
	@PostMapping("/register")
	public String createAccount(@RequestBody Accounts a) {
		if(repo.existsById(a.getCid())) {
			return "Account already exists";
		}
			repo.save(a);
			return "Account record created";
	}
	@PutMapping("/changepwd/{cid}/{abc}/{xyz}")
	public String changePassword(@PathVariable int cid,@PathVariable String abc,@PathVariable String xyz) {
		if(repo.existsById(cid)) {
			 Optional<Accounts> optionalAccount = repo.findById(cid);
			 Accounts account = optionalAccount.get();
	            if (account.getPassword().equals(abc)) {
	                account.setPassword(xyz);
	                repo.save(account);
	                return "Password changed successfully";
	            }else {
	            	return "Password could not be changes";
	            }
		}else {
			return "account does not exsist";
		}
			
	}
	@GetMapping("/balance/{acc_no}")
	public double getAccountBalance(@PathVariable int acc_no) {
		 Optional<Accounts> optionalAccount = repo.findById(acc_no);

		    if (optionalAccount.isPresent()) {
		        return optionalAccount.get().getAmount();
		    } else {
		        return 0.0;
		    }
	}
		

	@PostMapping("/transfer")
	public String transferAmount(@RequestParam int fromAccountNo,@RequestParam int toAccountNo, @RequestParam int ifsc,@RequestParam int amount) {

		    Optional<Accounts> FromAccount = repo.findByAccNo(fromAccountNo);
		    Optional<Accounts> ToAccount = repo.findByAccNo(toAccountNo);

		    if (FromAccount.isPresent() && ToAccount.isPresent()) {
		        Accounts fromAccount = FromAccount.get();
		        Accounts toAccount = ToAccount.get();

		        if (fromAccount.getAmount() >= amount) {
		            fromAccount.setAmount(fromAccount.getAmount() - amount);
		            toAccount.setAmount(toAccount.getAmount() + amount);

		            repo.save(fromAccount);
		            repo.save(toAccount);

		            return "Transferred Rs. " + amount + " successfully";
		        } else {
		            return "Insufficient balance for the transfer";
		        }
		    } else {
		        return "Accounts not found";
		    }
	}
	
	@GetMapping("/accounts/below")
	public List<Accounts> getAccountsBelowBalance(int amount) {
       return repo.findByAmountLessThan(amount);
       
    }
	
	@GetMapping("/accounts/above")
	public List<Accounts> getAccountsAboveBalance(int amount) {
       return repo.findByAmountGreaterThan(amount);
       
    }
}
