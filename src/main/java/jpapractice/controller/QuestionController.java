package jpapractice.controller;

import jpapractice.domain.Answer;
import jpapractice.domain.ProductQuestion;
import jpapractice.repository.AnswerRepository;
import jpapractice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/question")
    public ResponseEntity<?> init() {
        log.info("일반 쿼리 시작");
        List<Answer> answerList = answerRepository.findAll();
        answerList.forEach(a -> a.getQuestion().getMessage());
        log.info("일반 쿼리 끝");

        log.info("일반 쿼리 시작2");
        List<ProductQuestion> questionList = questionRepository.findAll();
        questionList.forEach(q-> q.getAnswerList().forEach(aa->aa.getMessage()));
        log.info("일반 쿼리 끝2");

        return ResponseEntity.ok("쿼리 성공");
    }

    @GetMapping("/question2")
    public ResponseEntity<?> init2() {

        log.info("JPQL 쿼리 시작");
        List<Answer> answerList = answerRepository.findAllFetch();
        answerList.forEach(a -> a.getQuestion().getMessage());
        log.info("JPQL 쿼리 끝");

        log.info("JPQL 쿼리 시작2");
        List<ProductQuestion> questionList = questionRepository.findAllFetch();
        // List 라면 카티션 곱 발생, 데이터 중복, 자료구조를 List말고 LinkedHashSet으로 바꾸면 해결 가능
        questionList.forEach(q-> q.getAnswerList().forEach(a-> a.getMessage()));
        log.info("JPQL 쿼리 끝2");

        return ResponseEntity.ok("쿼리 성공2");
    }

}
