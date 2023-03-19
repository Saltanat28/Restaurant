package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.RestaurantService;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/restaurants")
public class RestaurantApi {
    private final RestaurantService restaurantService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRest(@RequestBody RestaurantRequest restaurantRequest){
        return  restaurantService.saveRest(restaurantRequest);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public RestaurantResponse getById(@PathVariable Long id){
        return restaurantService.getByRestId(id);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public SimpleResponse delete(@PathVariable Long resId){
        return restaurantService.deleteRest(resId);

    }
    @PutMapping
    public SimpleResponse update(@PathVariable Long resId,
                                     @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.updateRest( restaurantRequest, resId);
    }
}
