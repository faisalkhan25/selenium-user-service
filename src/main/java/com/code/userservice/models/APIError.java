package com.code.userservice.models;


import java.time.LocalDateTime;

public record APIError(LocalDateTime timestamp, Integer statusCode, String errorMessage, String message, String path) {
}
