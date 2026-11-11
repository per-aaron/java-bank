package org.example.bank;

import java.time.LocalDate;

public class Transaction {
    LocalDate date;
    int amount;
    int balance;
    String type = "";

    public Transaction(LocalDate transactionDate, int amount, int oldBalance){
        this.date = transactionDate;
        this.amount = amount;
        this.balance = oldBalance + amount;
        if (this.amount >= 0){
            this.type = "deposit";
        } else {
            type = "withdrawal";
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

}
