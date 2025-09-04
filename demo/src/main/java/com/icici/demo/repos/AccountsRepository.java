package com.icici.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icici.demo.entities.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    
}
