package org.example.bank;

import java.time.LocalDate;

public class Transaction {
    final LocalDate date;
    final int amount;
    final int balance;
    final String type;

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

    public int getAmount() {
        return this.amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getType() {
        return this.type;
    }

}
