package me.sora.younote.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.sora.younote.dto.ApplicationResponse;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GetUserByIdResponse extends ApplicationResponse {
    private GetUserByIdData data;

    @Data
    @Builder
    public static class GetUserByIdData {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
    }
}
