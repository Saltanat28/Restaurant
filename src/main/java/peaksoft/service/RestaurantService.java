package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;

import java.util.List;

@Service
public interface RestaurantService {
    SimpleResponse saveRest(RestaurantRequest restaurantRequest);


    RestaurantResponse getByRestId(Long id);

    SimpleResponse deleteRest(Long resId);

    SimpleResponse updateRest(RestaurantRequest restaurantRequest, Long id);

}
