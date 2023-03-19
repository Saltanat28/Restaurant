package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;
@Builder
public record UserResponse(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email,
        String password,
        int phoneNumber,
        Role role,
        int experience
) {
}
