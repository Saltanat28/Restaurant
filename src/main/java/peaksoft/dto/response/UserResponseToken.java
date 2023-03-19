package peaksoft.dto.response;

import jakarta.persistence.Entity;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record UserResponseToken(

        String email,
        String token
) {
}
