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

        memberRepository.save(Member.builder().name("트랜잭션유저").build());
        transactionService.saveQuestion();

        // 예외가 발생했음에도 member, question 데이터는 잘 추가됨
        // sendMail()의 transaction 속성이 Requires_new 로 설정되었기 때문
        try {
            transactionService.sendMail();
        } catch (Exception e) {
            log.info("예외 처리");
        }
    }
}

