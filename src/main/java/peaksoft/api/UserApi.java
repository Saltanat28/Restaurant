package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.AcceptForJob;
import peaksoft.dto.request.ResumeForJob;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.dto.response.UserResponseToken;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserApi {
    private final UserService userService;
    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }
//    @PreAuthorize("hasAuthority('ADMIN')")


    @PostMapping("login")
    public UserResponseToken login(@RequestBody UserRequest userRequest){
       return userService.authenticate(userRequest);
    }



    @PostMapping("user")
    @PreAuthorize("hasAuthority('ADMIN')")

    public SimpleResponse saveUser(@RequestBody UserRequest userRequest){
        return userService.save(userRequest);
    }
    @PostMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveInRest(@RequestBody UserRequest userRequest,
                                     @PathVariable Long restaurantId){
     return userService.saveInRestaurant(userRequest, restaurantId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable Long userId){
      return userService.getByUserId(userId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public SimpleResponse deleteByUserId(@PathVariable Long userId){
        return userService.deleteById(userId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}")
    public SimpleResponse updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        return userService.update(userId,userRequest);
    }
    @PostMapping
    public SimpleResponse getForJob(@RequestBody ResumeForJob resumeForJob){
        return userService.getForJob(resumeForJob);
    }
    @GetMapping
    public List<SimpleResponse> getAllResume(){
        return userService.readResume();
    }







    public SimpleResponse acceptForJob(AcceptForJob acceptForJob){
        return userService.acceptUser(acceptForJob);
    }

}
