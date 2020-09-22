package ee.swan.exception.handler;

import ee.swan.exception.AuthFailureException;
import ee.swan.exception.UserNotFoundException;
import ee.swan.exception.model.ApiErrorDetail;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDetail> handleUserNotFoundException(UserNotFoundException unfe) {
        ApiErrorDetail errorDetail = new ApiErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setCode(1002);
        errorDetail.setMessage(unfe.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiErrorDetail> handleAuthFailureException(AuthFailureException afe) {
        ApiErrorDetail errorDetail = new ApiErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setCode(1004);
        errorDetail.setMessage(afe.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }
}
