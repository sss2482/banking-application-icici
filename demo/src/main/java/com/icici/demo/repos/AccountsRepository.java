package com.icici.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icici.demo.entities.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    
}
