package me.sora.younote.controller.advice;

import me.sora.younote.dto.ApplicationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static me.sora.younote.constant.ErrorConstant.ErrorCode.ERROR_CODE_UNABLE_TO_PROCEED;
import static me.sora.younote.constant.ServiceConstant.ResponseStatus.SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationResponse> handleException(Exception e) {
        var response = ApplicationResponse.builder()
                .status(SERVER_ERROR)
                .error(ApplicationResponse.ErrorResponse.builder()
                        .code(ERROR_CODE_UNABLE_TO_PROCEED)
                        .message(e.getMessage())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationResponse> handleException(ApplicationException e) {
        var response = ApplicationResponse.builder()
                .status(e.getStatus())
                .error(ApplicationResponse.ErrorResponse.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
