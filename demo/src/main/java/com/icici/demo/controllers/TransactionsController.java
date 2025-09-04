package com.icici.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.icici.demo.entities.Accounts;
import com.icici.demo.entities.Transactions;
import com.icici.demo.repos.TransactionsRepository;
import com.icici.demo.repos.AccountsRepository;
import lombok.extern.slf4j.Slf4j;

//import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
public class TransactionsController {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountsRepository accountRepository;
    @GetMapping("/transactions")
    public List<Transactions> fetchAllTransactions() {
        // logic to fetch from DB
        return transactionsRepository.findAll();
    }

     @GetMapping("/transactions/{id}")
    public Transactions fetchATransaction(@PathVariable("id") int id){
         Optional<Transactions> transactionsFound = transactionsRepository.findById(id);
         if(transactionsFound.isPresent()){
            log.debug("Transaction Found "+ transactionsFound.get());
            return transactionsFound.get();
         }
        else{
            log.warn("Transaction Not Found with id "+ id);
             throw new TransactionNotFoundException("Trip not found with id " + id);
        }
    }

    @PostMapping("/transactions/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransaction(@RequestBody Transactions transactions) {
        long senderAccountNumber = transactions.getSenderAccount().getAccountNumber();
        Optional<Accounts> senderAccount= accountRepository.findById(senderAccountNumber);
        if(!senderAccount.isPresent()){
            log.warn("sender account not found");
            throw new AccountNotFoundException("sender account not found with id " + senderAccountNumber);
        }
        long receiverAccountNumber = transactions.getReceiverAccount().getAccountNumber();
        Optional<Accounts> receiverAccount= accountRepository.findById(receiverAccountNumber);
        if(!receiverAccount.isPresent()){
            log.warn("receiver account not found");
            throw new AccountNotFoundException("receiver account not found with id " + receiverAccountNumber);
        }
        Accounts senderAccountMain=senderAccount.get();
        Accounts receiverAccountMain=receiverAccount.get();
        // System.out.println(senderAccount);
        int transactionAmount=transactions.getAmount();
        // System.out.println(transactionAmount);
        
        // System.out.println(receiverAccount);
        if(transactionAmount>senderAccountMain.getBalance()){
            log.warn("sender does not have this much money");
            
        }
        double senderFinalBalance=senderAccountMain.getBalance()-transactionAmount;
        double receiverFinalBalance=receiverAccountMain.getBalance()+transactionAmount;
        senderAccountMain.setBalance(senderFinalBalance);
        receiverAccountMain.setBalance(receiverFinalBalance);
        accountRepository.save(senderAccountMain);
        accountRepository.save(receiverAccountMain);
        transactions.setSenderAccount(senderAccountMain);
        transactions.setReceiverAccount(receiverAccountMain);
        transactionsRepository.save(transactions);
        
    }


}

