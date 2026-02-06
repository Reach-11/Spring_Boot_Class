package org.example.spring_boot_learning.exception;
//RuntimeException is an unchecked exception, which means that it does not need to be declared in a method's throws clause and can be thrown without being caught. It is typically used for exceptions that indicate programming errors or unexpected conditions that are not expected to be recoverable.
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
