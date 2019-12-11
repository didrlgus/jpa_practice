package jpapractice.service;

import jpapractice.domain.Member;
import jpapractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TransactionService transactionService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExample() {
        /*Optional<Member> memberOpt = memberRepository.findById(1L);
        Member member = memberOpt.get();
        // dirty check
        member.setName("양기현123");*/

        // 서비스 메서드에서 @Transactional 설정하면 repository쪽의 save 메서드도 모두 같은 트랜잭션으로 전파됨
        memberRepository.save(Member.builder().name("트랜잭션유저").build());
        transactionService.saveQuestion();

        // 예외가 발생했음에도 member, question 데이터는 잘 추가됨
        // sendMail()의 transaction 속성이 Requires_new 로 설정되었기 때문
        try {
            transactionService.sendMail();
        } catch (Exception e) {
            log.info("예외 처리");
        }

        // 다른 Bean의 트랜잭션 메서드를 호출해야 적용됨
        /*try {
            sendMail2();
        } catch (Exception e) {
            log.info("예외 처리2");
        }*/
    }

    public void transactionExample2() {

        // 서비스 메서드에서 @Transactional 설정안하면 repository쪽의 save 메서드도 모두 repository에서 트랜잭션이 시작됨
        // 트랜잭션 시작
        memberRepository.save(Member.builder().name("트랜잭션유저").build());
        log.info("#### {}", memberRepository.getClass());
        // 트랜잭션 끝

        // 트랜잭션 시작
        // transactionService.saveQuestion();
        log.info("#### {}", memberRepository.getClass());
        // 트랜잭션 끝

        try {
            log.info("#### {}", transactionService.getClass());
            transactionService.sendMail();
        } catch (Exception e) {
            log.info("예외 처리");
        }
    }

    /*@Transactional(propagation = Propagation.REQUIRED)
    public void sendMail2() {
        throw new NullPointerException("예외발생");
    }*/
}

