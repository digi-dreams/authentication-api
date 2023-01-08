package src.authenticationapi.modules.common.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Exception ex) {
        super(message, ex);
    }
}
