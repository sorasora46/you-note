package me.sora.younote.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponse {
    private Integer status;
    private ErrorResponse error;

    @Data
    @Builder
    public static class ErrorResponse {
        private Integer code;
        private String message;
    }
}
