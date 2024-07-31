package davide.prelati.customProjectBACK.entities;

import davide.prelati.customProjectBACK.enums.Sponsor;
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
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "squad")
    private Set<Shirt> shirts;
    @Enumerated(EnumType.STRING)
    private Sponsor sponsor;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public Squad(String name, Sponsor sponsor) {
        this.name = name;
        this.shirts = shirts;
        this.sponsor = sponsor;
        this.nation = nation;
    }

    public void addNation(Nation nation) {
        this.nation = nation;
    }

}
