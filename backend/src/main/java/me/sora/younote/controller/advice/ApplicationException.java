package me.sora.younote.controller.advice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

import static me.sora.younote.constant.ErrorConstant.ErrorCode.ERROR_CODE_UNABLE_TO_PROCEED;
import static me.sora.younote.constant.ServiceConstant.ResponseStatus.SERVER_ERROR;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer status;
    private Integer code;

    public ApplicationException(String message) {
        this(SERVER_ERROR, ERROR_CODE_UNABLE_TO_PROCEED, message);
    }

    public ApplicationException(Integer code, String message) {
        this(SERVER_ERROR, code, message);
    }

    public ApplicationException(Integer status, Integer code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
