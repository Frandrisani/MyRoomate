package backend_myroommate.MyRoomate.entities;

import backend_myroommate.MyRoomate.enums.TypeRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String title;
    private String description;
    private double price;
    private String address;
    private String city;
    private String zipCode;
    private int bedrooms;
    private int wc;
    @Enumerated(EnumType.STRING)
    private TypeRoom type;
    private List<String> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "roomcoinquilino", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Roommate> coinquilinoRooms = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "room",  fetch = FetchType.EAGER)
    private List<FavoriteRoom> favoriteRooms = new ArrayList<>();

    // Constructor
    public Room(String title, String description, double price, String address, String city, String zipCode, int bedrooms, int wc, TypeRoom type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.bedrooms = bedrooms;
        this.wc = wc;
        this.type = type;
    }
}
