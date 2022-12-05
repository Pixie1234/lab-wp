package mk.ukim.finki.labwp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    private String username;

    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User() {
    }

    public User( String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.carts = new ArrayList<>();
    }
}
