package com.example.BankManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankManagementSystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
