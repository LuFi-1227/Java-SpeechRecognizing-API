package com.application.budgeting.domain;

import java.util.UUID;

/**
 * TransactionId
 */
public record TransactionId(UUID id) {
    public TransactionId(){
        this(UUID.randomUUID());
    }
}
