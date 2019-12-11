package jpapractice.controller;

import jpapractice.domain.Member;
import jpapractice.domain.Team;
import jpapractice.repository.MemberRepository;
import jpapractice.repository.TeamRepository;
import jpapractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *  ManyToOne OneToMany 양방향 매핑에서 생기는 N+1 과 해결 방법 (Member(N) -> Team(1), Team(1) -> Member(N))
 * 1. JPQL 패치조인
 * 2. QueryDsl 패치조인
 **/

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberService memberService;

    // n+1 문제가 발생하는 요청
    @GetMapping("/member")
    public ResponseEntity<?> init() {

        log.info("일반 쿼리 시작");
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(m -> m.getTeam().getName());
        log.info("일반 쿼리 끝");

        log.info("일반 쿼리 시작2");
        List<Team> teamList = teamRepository.findAll();
        teamList.forEach(t->log.info("##### {}",t.getMemberSet().getClass()));  // 프록시 객체
        teamList.forEach(t->log.info("##### {}",t.getCoachSet().getClass()));   // 프록시 객체
        teamList.forEach(t-> t.getMemberSet().forEach(m->m.getName()));
        log.info("일반 쿼리 끝2");

        return ResponseEntity.ok("쿼리 성공");
    }

    @GetMapping("/member2")
    public ResponseEntity<?> init2() {

        log.info("JPQL 쿼리 시작");
        List<Member> memberList = memberRepository.findAllFetch();
        memberList.forEach(m->log.info("##### {}", m.getTeam().getClass()));    // fetch join으로 가져온 실제 엔티티 객체
        memberList.forEach(m -> m.getTeam().getName());
        log.info("JPQL 쿼리 끝");

        log.info("JPQL 쿼리 시작2");
        Set<Team> teamSet = teamRepository.findAllFetch();
        teamSet.forEach(t-> t.getMemberSet().forEach(m->log.info("##### {}",m.getClass())));        // fetch join으로 가져온 실제 엔티티 객체
        teamSet.forEach(t-> t.getCoachSet().forEach(c->log.info("##### {}",c.getClass())));         // fetch join으로 가져오지 않은 coach 프록시 엔티티
        // List 라면 카티션 곱 발생, 데이터 중복, 자료구조를 List말고 LinkedHashSet으로 바꾸면 해결 가능
        teamSet.forEach(t-> t.getMemberSet().forEach(m-> System.out.println(m.getName())));
        teamSet.forEach(t-> t.getCoachSet().forEach(c->c.getName()));       // coach 엔티티는 fetch join으로 가져오지 않아 접근 시 N+1문제 그대로 발생
        log.info("JPQL 쿼리 끝2");

        return ResponseEntity.ok("쿼리 성공2");
    }

    @GetMapping("/member3")
    public ResponseEntity<?> init3() {

        log.info("QueryDsl 쿼리 시작");
        List<Member> memberList = memberRepository.getAllMemberList();
        memberList.forEach(m->m.getTeam().getName());
        log.info("QueryDsl 쿼리 끝");

        log.info("QueryDsl 쿼리 시작2");
        // List 라면 카티션 곱 발생, 데이터 중복, 자료구조를 List말고 LinkedHashSet으로 바꾸면 해결 가능
        Set<Team> teamList = new LinkedHashSet<>(teamRepository.getAllTeamList());
        teamList.forEach(t->t.getMemberSet().stream().sorted(((o1, o2) -> o1.getId().intValue()-o2.getId().intValue())).forEach(m-> System.out.println(m.getName())));
        log.info("QueryDsl 쿼리 끝2");

        return ResponseEntity.ok("쿼리 성공3");
    }

    @GetMapping("/transaction")
    public ResponseEntity<?> init4() {

        memberService.transactionExample();

        log.info("##### 프록시 : {}", memberService.getClass());
        return null;
    }

    @GetMapping("/transaction2")
    public ResponseEntity<?> init5() {

        memberService.transactionExample2();

        log.info("##### 프록시 : {}", memberService.getClass());
        return null;
    }
}
