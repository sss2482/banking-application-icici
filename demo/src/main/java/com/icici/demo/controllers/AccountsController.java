package com.icici.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.icici.demo.entities.Accounts;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class AccountsController {

    @Autowired
    AccountsRepository accountRepository;

    @GetMapping("/accounts")
    public List<Accounts> fetchAllAccounts() {
        // logic to fetch from DB
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Accounts fetchAAccount(@PathVariable("id") int id) {
        Optional<Accounts> accountFound = accountRepository.findById(id);
        if (accountFound.isPresent()) {
            log.debug("Account Found " + accountFound.get());
            return accountFound.get();
        } else {
            log.warn("Account Not Found with id " + id);
            throw new AccountNotFoundException("Account not found with id " + id);
        }
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccounts(@RequestBody Accounts accounts) {
        accountRepository.save(accounts);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable("id") int id) {
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/accounts/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItinerary(@RequestBody Accounts accounts) {
        accountRepository.save(accounts);
    }
}
