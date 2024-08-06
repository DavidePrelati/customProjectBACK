package davide.prelati.customProjectBACK.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @OneToMany(mappedBy = "nation")
    private Set<Squad> squads;

    public Nation(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public void addSquad(Squad squad) {
        squads.add(squad);
        squad.setNation(this);
    }
}
