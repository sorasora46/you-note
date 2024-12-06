package me.sora.younote.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserByIdResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
