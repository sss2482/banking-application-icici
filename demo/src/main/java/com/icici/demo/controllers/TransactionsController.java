package com.icici.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.icici.demo.entities.Transactions;
import com.icici.demo.repos.TransactionsRepository;

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

    @GetMapping("/transactions")
    public List<Transactions> fetchAllTransactions() {
        // logic to fetch from DB
        return transactionsRepository.findAll();
    }

     @GetMapping("/transactions/{id}")
    public Transactions fetchATrip(@PathVariable("id") int id){
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

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrips(@RequestBody Transactions transactions) {
        transactionsRepository.save(transactions);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTrip(@PathVariable("id") int id){
       try{
            transactionsRepository.deleteById(id);
       }
       catch(Exception e){
        e.printStackTrace();
       }
        
    }
}

