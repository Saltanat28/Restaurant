package peaksoft.dto.request;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import peaksoft.enums.Role;

import java.time.LocalDate;
@Builder
public record FindAllResume(
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
