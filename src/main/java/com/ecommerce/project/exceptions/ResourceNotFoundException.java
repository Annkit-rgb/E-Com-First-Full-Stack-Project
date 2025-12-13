package com.ecommerce.project.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String filed;
    String filedName;
    Long fieldId;

    public ResourceNotFoundException( ) {

    }

    public ResourceNotFoundException(String resourceName, String filed, String filedName) {

      super(String.format("%s not found with %s: %s", resourceName, filed, filedName));
        this.resourceName = resourceName;
        this.filed = filed;
        this.filedName = filedName;

    }

    public ResourceNotFoundException(String resourceName, String filed, Long fieldId) {
        super(String.format("%s not found with %s: %d", resourceName, filed, fieldId));
        this.resourceName = resourceName;
        this.filed = filed;
        this.fieldId = fieldId;
    }

}
