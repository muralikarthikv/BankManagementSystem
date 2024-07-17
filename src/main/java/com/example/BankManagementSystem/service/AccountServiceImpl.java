package com.example.BankManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankManagementSystem.entity.Account;
import com.example.BankManagementSystem.repo.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository repo;
	@Override
	public Account createAccount(Account account) {
		Account account_saved=repo.save(account);
		// TODO Auto-generated method stub
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			new RuntimeException("Account Does Not Exist");
		}
		Account account_found=account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		// TODO Auto-generated method stub
		List<Account> listOfAccounts = repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		// TODO Auto-generated method stub
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			new RuntimeException("Account Does Not Exist");
		}
		Account accountPresent=account.get();
		Double total_balance=accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(total_balance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			new RuntimeException("Account Does Not Exist");
		}
		Account accountPresent=account.get();
		Double total_balance=accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(total_balance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		
	}

}
