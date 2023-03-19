package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.NoSuchElementException;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public SimpleResponse saveRest(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setNumberOfEmployees(0);
        restaurant.setService(restaurantRequest.service());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .massage(String.format(restaurantRequest.name() +  "   restaurant successfully saved! ")).build();
    }


    @Override

    public RestaurantResponse getByRestId(Long id) {
        return restaurantRepository.getByRestId(id).orElseThrow(() -> new NoSuchElementException("Not found!!!"));
    }




    @Override
    public SimpleResponse updateRest(RestaurantRequest restaurantRequest, Long restaurantId) {
        Restaurant restaurant1 = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NoSuchElementException("Restaurant with id:" + restaurantId + " not found!"));
        restaurant1.setName(restaurantRequest.name());
        restaurant1.setService(restaurantRequest.service());
        restaurant1.setRestType(restaurantRequest.restType());
        restaurantRepository.save(restaurant1);
        return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).
                massage(String.format("Restaurant with id:" + restaurantId  + "  is  successfully deleted")).build();

    }

    @Override
    public SimpleResponse deleteRest(Long resId) {
        restaurantRepository.deleteById(resId);
        return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).
                massage(String.format("Restaurant with id:%s is  successfully deleted")).build();
    }
}

