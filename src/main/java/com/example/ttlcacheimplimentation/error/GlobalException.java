package com.example.ttlcacheimplimentation.error;

import com.example.ttlcacheimplimentation.model.ExceptionObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {



    // TODO: 08/12/2022 ошибка по пустому списку при запросе всех ключей
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionObject> handleNotFound(NotFoundException e) {
        return ResponseEntity.badRequest().body(new ExceptionObject());
    }
}
