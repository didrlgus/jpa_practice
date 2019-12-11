package jpapractice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Member> memberSet;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Coach> coachSet;
}
