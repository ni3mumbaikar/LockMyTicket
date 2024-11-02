package in.ni3mumbaikar.LockMyTicket.exception;

public enum CustomExceptionType {
    DB_EXCEPTION("Database exception occurred"),
    VALIDATION_EXCEPTION("Validation failed"),
    AUTH_EXCEPTION("Authentication exception occurred");

    private final String message;

    CustomExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
