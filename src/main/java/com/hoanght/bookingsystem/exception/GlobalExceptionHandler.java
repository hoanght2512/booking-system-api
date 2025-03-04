package com.hoanght.bookingsystem.exception;

import com.hoanght.bookingsystem.dto.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<Void> handleBadRequestException(BadRequestException e) {
        return DataResponse.error(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataResponse<Void> handleResourceNotFoundException(ResourceNotFoundException e) {
        return DataResponse.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<Void> handleMethodArgumentNotValidException() {
        return DataResponse.error("Bad request");
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DataResponse<Void> handleAuthenticationException() {
        return DataResponse.error("Unauthorized");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DataResponse<Void> handleBadCredentialsException() {
        return DataResponse.error("Bad credentials");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataResponse<Void> handleException(Exception e) {
        log.error("Internal server error", e);
        return DataResponse.error("Internal server error");
    }
}
