package davide.prelati.customProjectBACK.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import davide.prelati.customProjectBACK.enums.Sponsor;
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
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlImage;

    @OneToMany(mappedBy = "squad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Shirt> shirts = new HashSet<>();
    ;
    @Enumerated(EnumType.STRING)
    private Sponsor sponsor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id")
    @JsonIgnore
    private Nation nation;

    public Squad(String name, Sponsor sponsor, String urlImage, Nation nation) {
        this.name = name;
        this.urlImage = urlImage;
        this.sponsor = sponsor;
        this.nation = nation;
    }


}
