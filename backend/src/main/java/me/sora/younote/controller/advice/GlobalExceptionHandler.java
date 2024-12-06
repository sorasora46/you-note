package me.sora.younote.controller.advice;

import me.sora.younote.dto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Object>> handleException(Exception e) {
        return new ResponseEntity<>(CommonResponse.failed(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CommonResponse<Object>> handleException(ApplicationException e) {
        return new ResponseEntity<>(CommonResponse.failed(e.getMessage()), e.getHttpStatus());
    }

}
