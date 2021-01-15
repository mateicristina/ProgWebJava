package com.tennismatch.demo.exception;

public class EntityNotFoundException extends RuntimeException {

    private String entityName;

    public EntityNotFoundException(String entityName) {
        super(entityName + " not found!");
        this.entityName = entityName;
    }
}