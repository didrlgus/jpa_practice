package jpapractice.domain;

import lombok.*;

import javax.persistence.*;

// 단방향 관계 시 N+1 문제

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookStore bookStore;
}
