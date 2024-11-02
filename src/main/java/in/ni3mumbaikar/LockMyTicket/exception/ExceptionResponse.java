package in.ni3mumbaikar.LockMyTicket.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private final CustomExceptionType errorType;
    private final String errorMessage;
    private final HttpStatus status;

    public ExceptionResponse(CustomExceptionType errorType, HttpStatus status) {
        this.errorType = errorType;
        this.errorMessage = errorType.getMessage();
        this.status = status;
    }

    public ExceptionResponse(CustomExceptionType errorType, String errorMessage, HttpStatus status) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public CustomExceptionType getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
