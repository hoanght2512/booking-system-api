package com.hoanght.bookingsystem.exception;

import com.hoanght.bookingsystem.dto.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataResponse<Void> handleStorageFileNotFoundException(StorageFileNotFoundException e) {
        return DataResponse.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<Void> handleMethodArgumentNotValidException() {
        return DataResponse.error("Bad request");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<Void> handleMissingServletRequestParameterException() {
        return DataResponse.error("Bad request");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<Void> handleHttpMessageNotReadableException() {
        return DataResponse.error("Bad request");
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DataResponse<Void> handleAuthorizationDeniedException() {
        return DataResponse.error("Unauthorized");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DataResponse<Void> handleBadCredentialsException() {
        return DataResponse.error("Bad credentials");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataResponse<Void> handleNoResourceFoundException() {
        return DataResponse.error("Resource not found");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public DataResponse<Void> handleHttpRequestMethodNotSupportedException() {
        return DataResponse.error("Method not allowed");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataResponse<Void> handleException(Exception e) {
        log.error("Internal server error", e);
        return DataResponse.error("Internal server error");
    }
}
