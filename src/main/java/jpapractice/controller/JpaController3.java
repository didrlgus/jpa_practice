package jpapractice.controller;

import jpapractice.domain.Answer;
import jpapractice.domain.ProductQuestion;
import jpapractice.repository.AnswerRepository;
import jpapractice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JpaController3 {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/answer")
    public ResponseEntity<?> init() {

        Answer answer1 = Answer.builder().message("답글1").build();
        Answer answer2 = Answer.builder().message("답글2").build();
        Answer answer3 = Answer.builder().message("답글3").build();
        Answer answer4 = Answer.builder().message("답글4").build();
        Answer answer5 = Answer.builder().message("답글5").build();
        Answer answer6 = Answer.builder().message("답글6").build();

        ProductQuestion question1 = ProductQuestion.builder().message("댓글1").build();
        ProductQuestion question2 = ProductQuestion.builder().message("댓글2").build();
        ProductQuestion question3 = ProductQuestion.builder().message("댓글3").build();

        // answerr가 관계의 주인.
        // answer쪽에서 question을 설정하면 관계가 성립됨
        // answer쪽 (주인쪽) 설정 안해주면 매핑되지 않음
        answer1.setQuestion(question1);
        answer2.setQuestion(question1);
        answer3.setQuestion(question1);
        answer4.setQuestion(question1);
        answer5.setQuestion(question1);
        answer6.setQuestion(question2);

        List<Answer> answerList = new ArrayList<>();
        question1.setAnswerList(answerList);
        question1.getAnswerList().add(answer1);
        question1.getAnswerList().add(answer2);
        question1.getAnswerList().add(answer3);
        question1.getAnswerList().add(answer4);
        question1.getAnswerList().add(answer5);

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);

        return ResponseEntity.ok("댓글,답글 추가 완료");
    }

}
