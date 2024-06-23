package com.example.VirtualFitON.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BrandExceptionHandler {

    @ExceptionHandler(value={BrandNotFoundException.class})
    public ResponseEntity<Object> handleBrandNotFoundException
            (BrandNotFoundException brandNotFoundException)
    {
        BrandException brandException = new BrandException(
                brandNotFoundException.getMessage(),
                brandNotFoundException.getCause(),
                HttpStatus.NOT_FOUND

        );
        return new ResponseEntity<>(brandException, HttpStatus.NOT_FOUND);
    }
}
