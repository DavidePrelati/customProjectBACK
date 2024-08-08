package davide.prelati.customProjectBACK.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    @OneToMany(mappedBy = "nation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Squad> squads = new HashSet<>();

    public Nation(String name, String url) {
        this.name = name;
        this.url = url;

    }

}
