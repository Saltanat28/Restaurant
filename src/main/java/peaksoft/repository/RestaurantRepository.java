package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;

import peaksoft.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select new peaksoft.dto.response.RestaurantResponse(r.name,r.location,r.restType,r.service,r.numberOfEmployees)from Restaurant r where  r.id=:id")
    Optional<RestaurantResponse> getByRestId(Long id);
}