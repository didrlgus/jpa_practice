package jpapractice.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private ProductQuestion question;
}
