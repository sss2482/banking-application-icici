package com.icici.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icici.demo.entities.Accounts;
import com.icici.demo.entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
    Transactions findBySenderAccount(Accounts senderAccount);

    // sender_account
}
