package backend_myroommate.MyRoomate.entities;

import backend_myroommate.MyRoomate.enums.TypeRoom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
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
    private int roommates;
    private int wc;
    @Enumerated(EnumType.STRING)
    private TypeRoom type;
    private String image;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "roomcoinquilino")
    private Set<Roommate> coinquilinoRooms = new HashSet<>();

    // Constructor
    public Room(String title, String description, double price, String address, String city, String zipCode, int roommates, int wc, TypeRoom type, String image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.roommates = roommates;
        this.wc = wc;
        this.type = type;
        this.image = image;
    }
}
