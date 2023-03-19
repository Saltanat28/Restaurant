package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record RestaurantRequest(
         String name,
         String restType,
         String location,
         int service
) {
}
