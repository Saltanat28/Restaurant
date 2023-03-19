package peaksoft.service.serviceImpl;



import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.request.AcceptForJob;
import peaksoft.dto.request.ResumeForJob;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.SimpleResponse;

import peaksoft.dto.response.UserResponse;
import peaksoft.dto.response.UserResponseToken;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository,  RestaurantRepository restaurantRepository, PasswordEncoder encoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }




    @Override
    public UserResponseToken authenticate(UserRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );

        User user = userRepository.findByEmail(authRequest.email())
                .orElseThrow(() -> new NoSuchElementException(String.format
                        ("User with email: %s doesn't exists", authRequest.email())));
        String token = jwtUtil.generateToken(user);

        return UserResponseToken.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }

    @Override
    public SimpleResponse save(UserRequest userRequest) {
        User user = convert(userRequest);
        userRepository.save(user);

        return SimpleResponse.builder().status(HttpStatus.OK).massage(userRequest.firstName() + "successfully saved! ").build();
    }


    @PostConstruct
    public void init() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword(encoder.encode("admin"));
        user.setRole(Role.ADMIN);
        user.setFirstName("Saltanat");
        user.setLastName("Adilova");
        user.setExperience(4);
        user.setDateOfBirth(LocalDate.of(2002, 06, 28));
        user.setPhoneNumber(12345);
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }

    @Override
    public SimpleResponse saveInRestaurant(UserRequest userRequest, Long restaurantId) {
       Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new NoSuchElementException("Restaurant with id "+ restaurantId + "not found"));
        User user = convert(userRequest);
        restaurant.addUser(user);
        user.setRestaurant(restaurant);
        int count = restaurant.getUsers().size();
        restaurant.setNumberOfEmployees(count++);
        if(count > 15){
            throw  new RuntimeException(" No vacation");
        }
        userRepository.save(user);
        restaurantRepository.save(restaurant);

        return SimpleResponse.builder().status(HttpStatus.OK).massage("User successfully saved!").build();
    }

    @Override
    public UserResponse getByUserId(Long userId) {
       return userRepository.getByRestId(userId).orElseThrow(()->new NoSuchElementException("User with id:" + userId + "not found!"));

    }

    @Override
    public SimpleResponse deleteById(Long userId) {
        userRepository.deleteById(userId);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("User with id:" + userId + " successfully deleted").build();
    }

    @Override
    public SimpleResponse update(Long userId, UserRequest userRequest) {
        userRepository.getByRestId(userId).orElseThrow(()->new NoSuchElementException("User with id:" + userId + " not found!"));
        User user = convert(userRequest);
        userRepository.save(user);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("User with Id: "+ userId  + " successfully updated! ").build();
    }

    @Override
    public SimpleResponse getForJob(ResumeForJob resumeForJob) {
        User user = new User();
        user.setFirstName(resumeForJob.firstName());
        user.setLastName(resumeForJob.lastName());
        user.setEmail(resumeForJob.email());
        user.setPhoneNumber(resumeForJob.phoneNumber());
        user.setExperience(resumeForJob.experience());
        user.setPassword(resumeForJob.password());
        user.setRole(resumeForJob.role());
        user.setDateOfBirth(resumeForJob.dateOfBirth());
        int age = LocalDate.now().getYear()-resumeForJob.dateOfBirth().getYear();

        if(resumeForJob.role().equals(Role.CHEF)) {
            if(age <= 25 && age >= 45) {
                userRepository.save(user);
            }
        }else if (resumeForJob.role().equals(Role.WAITER)){
            if(age <= 18 && age >= 40){
                userRepository.save(user);
            }
        }else throw new RuntimeException();
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Wait!  your resume is checking").build();
    }

    @Override
    public List<SimpleResponse> readResume() {
        return userRepository.readAllResume();
    }

    @Override
    public SimpleResponse acceptUser(AcceptForJob acceptForJob) {
         User user = userRepository.findById(acceptForJob.userId()).orElseThrow(()->new NoSuchElementException("User not found!"));
         Restaurant restaurant = restaurantRepository.findById(acceptForJob.restaurantName()).orElseThrow(()-> new NoSuchElementException(" Restaurant with name " + acceptForJob.restaurantName() + "not found !"));
         int size = restaurant.getUsers().size();
         if(acceptForJob.massage().equalsIgnoreCase("accept")) {
             if (size < 15) {
                 user.setRestaurant(restaurant);
                 restaurant.addUser(user);
                 restaurant.setNumberOfEmployees(size++);
                 userRepository.save(user);
                 restaurantRepository.save(restaurant);
             }else if(acceptForJob.massage().equalsIgnoreCase("not Accept")){
                 userRepository.deleteById(acceptForJob.userId());
             }
         }

         return SimpleResponse.builder().status(HttpStatus.OK).massage("User accepted for jod ").build();
    }


    private User convert( UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setRole(userRequest.role());
        user.setExperience(userRequest.experience());
        user.setPassword(userRequest.password());
        user.setDateOfBirth(userRequest.dateOfBirth());
        return user;
    }
}
