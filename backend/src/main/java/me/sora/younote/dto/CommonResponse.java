package me.sora.younote.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sora.younote.constant.ServiceConstant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private Boolean status;
    private T result;
    private String message;

    public static <T> CommonResponse<T> success(T result) {
        return CommonResponse.<T>builder()
                .status(ServiceConstant.RESPONSE_STATUS.SUCCESS)
                .result(result)
                .build();
    }

    public static <T> CommonResponse<T> failed(String message) {
        return CommonResponse.<T>builder()
                .status(ServiceConstant.RESPONSE_STATUS.FAILED)
                .message(message)
                .build();
    }
}
