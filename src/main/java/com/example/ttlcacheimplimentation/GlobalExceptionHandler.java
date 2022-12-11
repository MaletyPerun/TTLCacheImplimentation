package com.example.ttlcacheimplimentation;

import com.example.ttlcacheimplimentation.error.BlankException;
import com.example.ttlcacheimplimentation.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleConflict(WebRequest request, NotFoundException e) {
        return createResponseEntity(request, ErrorAttributeOptions.defaults(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlankException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleBlankException(WebRequest request, BlankException e) {
        return createResponseEntity(request, e.getOptions(), e.getMessage(), e.getStatus());
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return createResponseEntity(request, ErrorAttributeOptions.defaults(), "Объект не должен быть пустым", HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unchecked")
    protected <T> ResponseEntity<T> createResponseEntity(WebRequest request, ErrorAttributeOptions options, String msg, HttpStatus status) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, options);
        if (msg != null) {
            body.put("message", msg);
        }
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return (ResponseEntity<T>) ResponseEntity.status(status).body(body);
    }
}
