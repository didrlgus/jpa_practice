package jpapractice.service;

import jpapractice.domain.Book;
import jpapractice.domain.ProductQuestion;
import jpapractice.repository.BookRepository;
import jpapractice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final QuestionRepository questionRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveQuestion() {
        questionRepository.save(ProductQuestion.builder().message("트랜잭션Question1").build());

        //throw new NullPointerException("예외발생");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendMail() {
        throw new NullPointerException("예외발생");
    }
}
