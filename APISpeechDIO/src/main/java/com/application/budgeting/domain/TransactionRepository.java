package com.application.budgeting.domain;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findAllByUserIdAndCategory(UUID userId, Category category);
}
