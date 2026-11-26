package com.application.budgeting.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.application.budgeting.domain.Category;
import com.application.budgeting.domain.Transaction;
import com.application.budgeting.domain.TransactionRepository;
import com.application.budgeting.infrastructure.persistence.entity.TransactionEntity;

@Repository 
public class JPATransactionRepository implements TransactionRepository {
    private TransactionEntityRepository repository;

    
    public JPATransactionRepository(TransactionEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return repository.save(entity).toDomain();

    }

    @Override
    public List<Transaction> findAllByUserIdAndCategory(UUID userId, Category category) {
        return repository.findAllByUserIdAndCategory(userId, category).stream().map(TransactionEntity::toDomain).toList();
    }
    
}
