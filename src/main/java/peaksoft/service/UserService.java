package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.AcceptForJob;
import peaksoft.dto.request.ResumeForJob;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.dto.response.UserResponseToken;

import java.util.List;

@Service
public interface UserService {
    UserResponseToken authenticate(UserRequest authRequest);


    SimpleResponse save(UserRequest userRequest);

    SimpleResponse saveInRestaurant(UserRequest userRequest, Long restaurantId);

    UserResponse getByUserId(Long userId);

    SimpleResponse deleteById(Long userId);

    SimpleResponse update(Long userId, UserRequest userRequest);

    SimpleResponse getForJob(ResumeForJob resumeForJob);

    List<SimpleResponse> readResume();


    SimpleResponse acceptUser(AcceptForJob acceptForJob);


//    UserResponse save(UserRequest userRequest, Long restaurantId);
//    UserResponseToken saveUser(UserRequest userRequest);


}
