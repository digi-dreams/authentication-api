package src.authenticationapi.modules.common.exception;

import lombok.Data;

@Data
public class MessageException {
    private String message;
    private String field;

    public MessageException() {
    }

    public MessageException(String message) {
        this.message = message;
    }

    public MessageException(String message, String field) {
        this.message = message;
        this.field = field;
    }
}
