package org.example.bank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Math.abs;

public class BankAccount {
    private int balance;
    ArrayList<Transaction> transactions = new ArrayList<>();
    private final DecimalFormat df = new DecimalFormat("0.00");

    public void deposit(int amount, LocalDate date){
        Transaction transaction = new Transaction(date, amount * 100, this.balance);
        transactions.add(transaction);
        this.balance = transaction.getBalance();
    }

    public void withdraw(int amount, LocalDate date){
        Transaction transaction = new Transaction(date, -amount * 100, this.balance);
        transactions.add(transaction);
        this.balance = transaction.getBalance();
    }

    public int getBankBalance() {
        return this.balance/100;
    }

    public String generateStatement(){
        // Sorts transactions by creating a comparator that compares the output of getDate() method on each transaction
        this.transactions.sort(Comparator.comparing(Transaction::getDate));
        //replaced concat with stringBuilder, considered better practice for performance and memory usage with fewer objects created
        StringBuilder output = new StringBuilder("date || credit || debit || balance\n");
        for (Transaction transaction : this.transactions.reversed()) {
            String amount = df.format(abs(transaction.getAmount()/100));
            String recordBalance = df.format(transaction.getBalance()/100);
            if (transaction.getType().equals("deposit")){
                //similarly chained appends prevents creation of new StringBuilder objects when compiler looks at concat in loops
                output.append(transaction.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(" || ").append(amount).append(" || ").append("-").append(" || ").append(recordBalance).append("\n");
            } else {
                output.append(transaction.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(" || ").append("-").append(" || ").append(amount).append(" || ").append(recordBalance).append("\n");
            }
        }
        return output.toString();
    }
}
