package backend_myroommate.MyRoomate.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"user", "listing"})
public class Roommate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "coinquilino_id")
    private User coinquilino;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomcoinquilino;

}