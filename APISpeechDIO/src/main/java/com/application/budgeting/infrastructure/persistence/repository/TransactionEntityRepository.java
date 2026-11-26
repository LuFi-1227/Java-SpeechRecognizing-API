package com.application.budgeting.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;
import com.application.budgeting.domain.Category;
import org.springframework.data.repository.CrudRepository;

import com.application.budgeting.infrastructure.persistence.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID>{
    public List<TransactionEntity> findAllByUserIdAndCategory(UUID userId, Category category);
}
