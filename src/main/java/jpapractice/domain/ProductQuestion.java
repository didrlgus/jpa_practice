package jpapractice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String message;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answerList;
}
