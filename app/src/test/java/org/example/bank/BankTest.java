package org.example.bank;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class BankTest {
    @Test
    public void generateStatementHasAllColumnHeadings() {
        BankAccount bankAccount = new BankAccount();
        Assert.assertEquals("date || credit || debit || balance\n", bankAccount.generateStatement());
    }

    @Test
    public void depositIncreasesBalance() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(1000, LocalDate.of(2021,1,10));
        Assert.assertEquals(1000, bankAccount.getBankBalance());
    }

    @Test
    public void withdrawDecreasesBalance() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(1000, LocalDate.of(2021,1,10));
        bankAccount.withdraw(400, LocalDate.of(2021, 1, 13));
        Assert.assertEquals(600, bankAccount.getBankBalance());
    }

    @Test
    public void generateStatementWorksForMultipleTransactions() {
        BankAccount bankAccount = new BankAccount();

        // Given a client makes a deposit of 1000 on 2021-01-10
        bankAccount.deposit(1000, LocalDate.of(2021, 1, 10));

        // And a deposit of 2000 on 2021-01-13
        bankAccount.deposit(2000, LocalDate.of(2021, 1, 13));

        // And a withdrawal of 500 on 2021-01-14
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));

        // When she prints her bank statement
        Assert.assertEquals(
                """
                        date || credit || debit || balance
                        14/01/2021 || - || 500.00 || 2500.00
                        13/01/2021 || 2000.00 || - || 3000.00
                        10/01/2021 || 1000.00 || - || 1000.00
                        """,
                bankAccount.generateStatement());
    }
}
