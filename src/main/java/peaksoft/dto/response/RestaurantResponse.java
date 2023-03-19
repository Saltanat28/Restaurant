package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record RestaurantResponse(
        String name,
        String restType,
        String location,
        int numberOfEmployees,
        int service
) {
}
