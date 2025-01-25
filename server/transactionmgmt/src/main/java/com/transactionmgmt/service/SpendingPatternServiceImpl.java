package com.transactionmgmt.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionmgmt.entity.Transaction;
import com.transactionmgmt.model.SpendingPattern;
import com.transactionmgmt.repository.TransactionRepository;

@Service
public class SpendingPatternServiceImpl implements SpendingPatternService {

    @Autowired
    private TransactionRepository transactionRepository;

    public SpendingPattern getSpendingPattern() {
        LocalDate now = LocalDate.now();
        LocalDate weekAgo = now.minusWeeks(1);
        LocalDate monthAgo = now.minusMonths(1);

        List<Transaction> weeklyTransactions = transactionRepository.findByDateBetween(weekAgo, now);
        List<Transaction> monthlyTransactions = transactionRepository.findByDateBetween(monthAgo, now);
        List<Transaction> allTransactions = transactionRepository.findAll();

        BigDecimal weeklySpending = calculateTotalSpending(weeklyTransactions);
        BigDecimal monthlySpending = calculateTotalSpending(monthlyTransactions);
        BigDecimal totalCurrentBalance = calculateTotalBalance(allTransactions);

        return new SpendingPattern(weeklySpending, monthlySpending, totalCurrentBalance);
    }

    public BigDecimal calculateTotalSpending(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getType().equals("INCOME") ? transaction.getAmount() : transaction.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
