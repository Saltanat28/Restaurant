package peaksoft.dto.request;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record ResumeForJob( String firstName,
         String lastName,
         LocalDate dateOfBirth,
         String email,
         String password,
         int phoneNumber,
         Role role,
         int experience                   ) {
}
