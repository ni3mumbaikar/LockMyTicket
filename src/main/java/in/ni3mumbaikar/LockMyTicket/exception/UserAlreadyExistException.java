package in.ni3mumbaikar.LockMyTicket.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String usernameAlreadyExists) {
        super(usernameAlreadyExists);
    }
}
