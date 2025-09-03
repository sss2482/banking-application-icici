package com.icici.demo.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int amount;
    LocalDate time;
    Long senderAccount;
    Long receiverAccount;

    // public int getId() {
    // return id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    // public int getAmount() {
    // return amount;
    // }

    // public void setAmount(int amount) {
    // this.amount = amount;
    // }

    // public Timestamp getTime() {
    // return time;
    // }

    // public void setTime(Timestamp time) {
    // this.time = time;
    // }

    // public Long getSender_account() {
    // return sender_account;
    // }

    // public void setSender_account(Long sender_account) {
    // this.sender_account = sender_account;
    // }

    // public Long getReceiver_account() {
    // return receiver_account;
    // }

    // public void setReceiver_account(Long receiver_account) {
    // this.receiver_account = receiver_account;
    // }

}
