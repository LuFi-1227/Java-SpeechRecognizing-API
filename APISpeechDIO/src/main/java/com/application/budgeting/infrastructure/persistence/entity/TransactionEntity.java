package com.application.budgeting.infrastructure.persistence.entity;

import java.util.UUID;

import com.application.budgeting.domain.Transaction;
import com.application.budgeting.domain.TransactionId;
import com.application.budgeting.domain.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransactionEntity {
    @Id 
    private UUID id;
    @Column (name = "user_id", nullable = false, updatable = false)
    private UUID userId;
    private String description;
    private long amount;
    @Enumerated (EnumType.STRING)
    private Category category;


    public TransactionEntity(UUID id, UUID userId, String description, long amount, Category category) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public static TransactionEntity from(Transaction transaction){
        return new TransactionEntity(
        transaction.getId().id(), 
        transaction.getUserId(),
        transaction.getDescription(),
        transaction.getAmount(),
        transaction.getCategory());
    }

    public Transaction toDomain() {
       return new Transaction(new TransactionId(this.id),
       this.userId,
       this.description,
       this.amount,
       this.category
        );
    }
}
