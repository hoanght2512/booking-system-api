package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    public DataResponse(boolean success, String message, T data) {
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }

    public static <T> DataResponse<T> ok(String message) {
        return new DataResponse<>(true, message, null);
    }

    public static <T> DataResponse<T> ok(String message, T data) {
        return new DataResponse<>(true, message, data);
    }

    public static <T> DataResponse<T> error(String message) {
        return new DataResponse<>(false, message, null);
    }
}
