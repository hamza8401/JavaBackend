package io.lightfeather.springtemplate;

import io.lightfeather.springtemplate.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler  {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> validationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        return new ResponseEntity<>(
                ApiError.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.BAD_REQUEST.toString())
                        .request(request.getRequestURI())
                        .requestType(request.getMethod())
                        .customMessage("Request is not valid")
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
