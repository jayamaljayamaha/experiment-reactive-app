package com.experiment.reactive.exception;

import com.experiment.reactive.common.InvalidImage;
import com.experiment.reactive.exception.exceptions.InvalidImageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(InvalidImageException.class)
//    public ResponseEntity<List<InvalidImage>> handleInvalidArguments(InvalidImageException exception){
//        return new ResponseEntity<>(exception.getInvalidImageList(), HttpStatus.BAD_REQUEST);
//    }
}
