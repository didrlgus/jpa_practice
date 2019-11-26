package jpapractice.repository;

import jpapractice.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Answer a join fetch a.question")
    List<Answer> findAllFetch();
}
