package backend_myroommate.MyRoomate.entities;


import backend_myroommate.MyRoomate.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"password", "role", "authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String birthdate;
    private int gender;
    private int usage;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String firstName, String lastName, String phoneNumber, String email, String birthdate, int gender, int usage, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.usage = usage;
        this.password = password;
        setDeafaultAvatar();
        this.role = UserRole.USER;
    }
    public void setDeafaultAvatar() {
    this.avatar = "https://ui-avatars.com/api/?name=" + this.firstName + "+" + this.lastName;
    }

    //    USER DETAILS OVERRIDES
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
