package org.example.bank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Math.abs;

public class BankAccount {
    int balance;
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static DecimalFormat df = new DecimalFormat("0.00");

    public void deposit(int amount, LocalDate date){
        Transaction transaction = new Transaction(date, amount * 100, this.balance);
        transactions.add(transaction);
        balance = transaction.balance;
    }

    public void withdraw(int amount, LocalDate date){
        Transaction transaction = new Transaction(date, -amount * 100, this.balance);
        transactions.add(transaction);
        balance = transaction.balance;
    }

    public String generateStatement(){
        // Sorts transactions by creating a comparator that compares the output of getDate() method on each transaction
        this.transactions.sort(Comparator.comparing(Transaction::getDate));
        String output = "date || credit || debit || balance\n";
        for (Transaction transaction : this.transactions.reversed()) {
            String amount = df.format(abs(transaction.amount/100));
            String recordBalance = df.format(transaction.balance/100);
            if (transaction.type == "deposit"){
                output += transaction.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " || " + amount + " || " + "-" + " || " + recordBalance + "\n";
            } else {
                output += transaction.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " || " + "-" + " || " + amount + " || " + recordBalance + "\n";
            }
        }
        return output;
    }
}
