package jpapractice.repository;

import jpapractice.domain.ProductQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<ProductQuestion, Long> {
    @Query("select q from ProductQuestion q join fetch q.answerList")
    List<ProductQuestion> findAllFetch();
}
