package in.ni3mumbaikar.LockMyTicket.advice;

import in.ni3mumbaikar.LockMyTicket.constant.StringConstants;
import in.ni3mumbaikar.LockMyTicket.exception.CustomExceptionType;
import in.ni3mumbaikar.LockMyTicket.exception.ExceptionResponse;
import in.ni3mumbaikar.LockMyTicket.exception.UserAlreadyExistException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvisor {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(CustomExceptionType.AUTH_EXCEPTION, StringConstants.USER_ALREADY_EXISTS_MSG, HttpStatus.CONFLICT);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ExceptionResponse> handlePSQLExceptionException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(CustomExceptionType.DB_EXCEPTION, ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
