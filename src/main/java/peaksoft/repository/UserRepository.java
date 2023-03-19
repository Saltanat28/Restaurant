package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.request.ResumeForJob;

import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
       Boolean existsByEmail(String email);
       Optional<User>  findByEmail(String email);
    @Query("select new peaksoft.dto.response.UserResponse(u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience)from User u where  u.id=:id")
    Optional<UserResponse> getByRestId(Long id);
    @Query("select new peaksoft.dto.response.UserResponse(u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience)from User u where  u.id=:id")
    List<SimpleResponse> readAllResume();



}
