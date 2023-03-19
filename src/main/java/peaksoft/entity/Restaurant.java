package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id-gen")
    @SequenceGenerator(name = "restaurant_id_gen", sequenceName = "restaurant_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int service;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<MenuItem>menuItems;


    public void addUser(User user) {
        this.users.add(user);
    }
}

