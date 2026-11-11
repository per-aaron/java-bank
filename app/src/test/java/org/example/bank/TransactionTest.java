package org.example.bank;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TransactionTest {
    @Test
    public void transactionStoresCreditCorrectly(){
        Transaction transaction = new Transaction(LocalDate.of(2021,1,10), 100, 0);
        Assert.assertEquals(LocalDate.of(2021,1,10), transaction.date);
        Assert.assertEquals(100, transaction.balance, 0.001);
        Assert.assertEquals("deposit", transaction.type);
    }

    @Test
    public void transactionStoresDebitCorrectly(){
        Transaction transaction = new Transaction(LocalDate.of(2023,12,31), -200, 200);
        Assert.assertEquals(LocalDate.of(2023,12,31), transaction.date);
        Assert.assertEquals(0, transaction.balance, 0.001);
        Assert.assertEquals("withdrawal", transaction.type);
    }

}
